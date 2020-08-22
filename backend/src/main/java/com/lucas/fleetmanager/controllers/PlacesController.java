package com.lucas.fleetmanager.controllers;

import com.lucas.fleetmanager.dto.ResponseDTO;
import com.lucas.fleetmanager.services.PlacesService;
import com.lucas.fleetmanager.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/places")
public class PlacesController {

    private final PlacesService service;

    @Autowired
    public PlacesController(PlacesService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        var response = ResponseUtils.buildOK(service.findAll());
        return ResponseEntity.ok(response);
    }

}
