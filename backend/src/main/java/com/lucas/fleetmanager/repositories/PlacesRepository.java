package com.lucas.fleetmanager.repositories;

import com.lucas.fleetmanager.entities.Places;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacesRepository extends MongoRepository<Places, String> {
}
