package com.lucas.fleetmanager.repositories;

import com.lucas.fleetmanager.entities.Trucks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrucksRepository extends MongoRepository<Trucks, String> {
    Trucks findByLicensePlate(String licensePlate);
}
