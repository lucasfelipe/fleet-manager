package com.lucas.fleetmanager.services.impl;

import com.lucas.fleetmanager.entities.Places;
import com.lucas.fleetmanager.repositories.PlacesRepository;
import com.lucas.fleetmanager.services.PlacesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacesServiceImpl implements PlacesService {

    private final PlacesRepository repository;

    @Autowired
    public PlacesServiceImpl(PlacesRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Places> findAll() {
        return repository.findAll();
    }
}
