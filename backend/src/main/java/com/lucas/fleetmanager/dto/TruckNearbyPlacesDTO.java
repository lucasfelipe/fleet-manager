package com.lucas.fleetmanager.dto;

import com.lucas.fleetmanager.consumer.dto.GoogleNearbyPlacesDTO;
import com.lucas.fleetmanager.consumer.dto.GooglePlaceDTO;
import com.lucas.fleetmanager.entities.Trucks;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class TruckNearbyPlacesDTO {

    Trucks truck;
    List<GooglePlaceDTO> nearByPlaces;

}
