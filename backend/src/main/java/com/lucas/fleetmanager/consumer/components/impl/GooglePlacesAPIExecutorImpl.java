package com.lucas.fleetmanager.consumer.components.impl;

import com.lucas.fleetmanager.consumer.components.GooglePlacesAPIExecutor;
import com.lucas.fleetmanager.consumer.dto.GoogleNearbyPlacesDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GooglePlacesAPIExecutorImpl implements GooglePlacesAPIExecutor {

    @Value("${google.maps.uri}")
    private String mapsURI;

    @Value("${google.places.api.key}")
    private String apiKey;

    @Override
    public GoogleNearbyPlacesDTO execute(String pathWithQueryParams) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getURL(pathWithQueryParams);
        return restTemplate.getForObject(url, GoogleNearbyPlacesDTO.class);
    }

    private String getURL(String pathWithQueryParams) {
        return mapsURI +
                pathWithQueryParams +
                "&key=" +
                apiKey;
    }
}
