package com.lucas.fleetmanager.consumer.service;

import com.lucas.fleetmanager.consumer.dto.GooglePlaceDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface GooglePlacesAPIConsumer {

    List<GooglePlaceDTO> getPlacesNearbyByLocation(List<String> typeOfPlaces, Double latitude, Double longitude, Double radius) throws ExecutionException, InterruptedException;

}
