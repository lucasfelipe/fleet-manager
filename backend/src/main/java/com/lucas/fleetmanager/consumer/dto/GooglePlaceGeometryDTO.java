package com.lucas.fleetmanager.consumer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GooglePlaceGeometryDTO implements Serializable {

    GooglePlaceLocationDTO location;

}
