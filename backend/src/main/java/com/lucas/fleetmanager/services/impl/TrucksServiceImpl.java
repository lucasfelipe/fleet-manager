package com.lucas.fleetmanager.services.impl;

import com.lucas.fleetmanager.consumer.service.GooglePlacesAPIConsumer;
import com.lucas.fleetmanager.dto.TruckNearbyPlacesDTO;
import com.lucas.fleetmanager.repositories.TrucksRepository;
import com.lucas.fleetmanager.services.TrucksService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Log4j2
@Service
public class TrucksServiceImpl implements TrucksService {

    private final TrucksRepository repository;
    private final GooglePlacesAPIConsumer googlePlacesAPIConsumer;

    @Autowired
    public TrucksServiceImpl(TrucksRepository repository, GooglePlacesAPIConsumer googlePlacesAPIConsumer) {
        this.repository = repository;
        this.googlePlacesAPIConsumer = googlePlacesAPIConsumer;
    }

    public TruckNearbyPlacesDTO findByTruckLicensePlateNearByPlaces(String licensePlate, List<String> poiTypes, Double radius) throws ExecutionException, InterruptedException {
        var truck = repository.findByLicensePlate(licensePlate);
        log.info("Truck found: {}", truck);

        var lastLocation = truck.getLocations().get(truck.getLocations().size() - 1);
        var placesNearby = googlePlacesAPIConsumer.getPlacesNearbyByLocation(poiTypes, lastLocation.getX(), lastLocation.getY(), radius);
        log.info("Places nearBy: {}", placesNearby);

        return TruckNearbyPlacesDTO
                .builder()
                .truck(truck)
                .nearByPlaces(placesNearby)
                .build();
    }
}
