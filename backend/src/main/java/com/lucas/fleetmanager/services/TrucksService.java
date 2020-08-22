package com.lucas.fleetmanager.services;

import com.lucas.fleetmanager.dto.TruckNearbyPlacesDTO;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface TrucksService {

    TruckNearbyPlacesDTO findByTruckLicensePlateNearByPlaces(String licensePlate, List<String> poiTypes, Double radius) throws ExecutionException, InterruptedException;

}
