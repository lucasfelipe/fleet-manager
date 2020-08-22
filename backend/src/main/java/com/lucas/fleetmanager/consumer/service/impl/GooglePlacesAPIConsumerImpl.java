package com.lucas.fleetmanager.consumer.service.impl;

import com.lucas.fleetmanager.consumer.components.GooglePlacesAPIExecutor;
import com.lucas.fleetmanager.consumer.dto.GoogleNearbyPlacesDTO;
import com.lucas.fleetmanager.consumer.dto.GooglePlaceDTO;
import com.lucas.fleetmanager.consumer.service.GooglePlacesAPIConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GooglePlacesAPIConsumerImpl implements GooglePlacesAPIConsumer {

    private final GooglePlacesAPIExecutor executor;
    private static final String API_NEARBY_URL = "/api/place/nearbysearch/json?";

    @Autowired
    public GooglePlacesAPIConsumerImpl(GooglePlacesAPIExecutor executor) {
        this.executor = executor;
    }

    @Override
    public List<GooglePlaceDTO> getPlacesNearbyByLocation(List<String> typeOfPlaces, Double latitude, Double longitude, Double radius) throws ExecutionException, InterruptedException {
        CompletableFuture<GoogleNearbyPlacesDTO>[] asyncCalls = createNearByLocationAsyncCalls(typeOfPlaces, latitude, longitude, radius);

        CompletableFuture<Void> allCalls = CompletableFuture.allOf(asyncCalls);

        CompletableFuture<List<GoogleNearbyPlacesDTO>> allResponses = allCalls.thenApplyAsync(f ->
                Stream.of(asyncCalls).map(CompletableFuture::join).collect(Collectors.toList())
        );

        CompletableFuture<List<GooglePlaceDTO>> responsesMapped = allResponses.thenApplyAsync(responses ->
                responses.stream().map(GoogleNearbyPlacesDTO::getResults).findFirst().orElseGet(Collections::emptyList)
        );

        return responsesMapped.get();
    }

    private CompletableFuture[] createNearByLocationAsyncCalls(List<String> typeOfPlaces, Double latitude, Double longitude, Double radius) {
        return typeOfPlaces.stream()
                .map(typeOfPlace -> getPathWithParamenters(typeOfPlace, latitude, longitude, radius))
                .map(this::createAsyncRequestGooglePlacesNearByLocation)
                .toArray(CompletableFuture[]::new);
    }

    private CompletableFuture<GoogleNearbyPlacesDTO> createAsyncRequestGooglePlacesNearByLocation(String pathWithParams) {
        return CompletableFuture.supplyAsync(() -> executor.execute(pathWithParams));
    }

    private String getPathWithParamenters(String typeOfPlace, Double latitude, Double longitude, Double radius) {
        return API_NEARBY_URL
                .concat("type=")
                .concat(typeOfPlace)
                .concat("&location=")
                .concat(
                        String.valueOf(latitude).concat(",").concat(String.valueOf(longitude))
                )
                .concat("&radius=")
                .concat(String.valueOf(radius));
    }
}
