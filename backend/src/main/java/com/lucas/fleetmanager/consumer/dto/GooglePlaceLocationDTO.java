package com.lucas.fleetmanager.consumer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GooglePlaceLocationDTO implements Serializable {

    Double lat;
    Double lng;

}
