package com.lucas.fleetmanager.consumer.components;

import com.lucas.fleetmanager.consumer.dto.GoogleNearbyPlacesDTO;
import com.lucas.fleetmanager.consumer.dto.GooglePlaceDTO;

public interface GooglePlacesAPIExecutor {

    GoogleNearbyPlacesDTO execute(String pathWithQueryParams);

}
