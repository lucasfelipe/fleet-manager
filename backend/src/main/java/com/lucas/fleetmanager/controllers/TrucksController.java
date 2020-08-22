package com.lucas.fleetmanager.controllers;

import com.lucas.fleetmanager.dto.ResponseDTO;
import com.lucas.fleetmanager.services.TrucksService;
import com.lucas.fleetmanager.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/trucks")
public class TrucksController {

    private final TrucksService service;

    @Autowired
    public TrucksController(TrucksService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> search(
            @RequestParam String licensePlate,
            @RequestParam List<String> poiTypes,
            @RequestParam Double radius) throws ExecutionException, InterruptedException {
        var response = ResponseUtils.buildOK(service.findByTruckLicensePlateNearByPlaces(licensePlate, poiTypes, radius));
        return ResponseEntity.ok(response);
    }
}
