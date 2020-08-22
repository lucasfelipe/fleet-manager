package com.lucas.fleetmanager.consumer.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoogleNearbyPlacesDTO implements Serializable {

    private List<GooglePlaceDTO> results;

}
