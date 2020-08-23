package com.lucas.fleetmanager.repositories;

import com.lucas.fleetmanager.config.MongoDBConfigurationTest;
import com.lucas.fleetmanager.entities.Trucks;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
class TrucksRepositoryTest {

    public static final String DISPLAY_GET_TRUCK_BY_LICENSE_TEST = "Given a license plate when try to find a truck by repo then check if the license is the same";
    public static final String LICENSE_PLATE = "VW202001";
    final TrucksRepository repository;
    private static Trucks truck;

    @Autowired
    public TrucksRepositoryTest(TrucksRepository repository) {
        this.repository = repository;
    }

    @BeforeAll
    static void openConnection() {
        MongoDBConfigurationTest.startMongoClient();
    }

    @BeforeEach
    void setup() {
        truck = Trucks
                .builder()
                .licensePlate(LICENSE_PLATE)
                .driver("ANY_DRIVER")
                .build();
        repository.insert(truck);
    }

    @DisplayName(DISPLAY_GET_TRUCK_BY_LICENSE_TEST)
    @Test
    void testFindTruckByLicensePlateShouldReturnObject() {
        var truck = repository.findByLicensePlate(LICENSE_PLATE);
        assertNotNull(truck);
        assertEquals(LICENSE_PLATE, truck.getLicensePlate());
        truck.setId(truck.getId());
    }

    @AfterEach
    void tearDown() {
        repository.delete(truck);
    }

    @AfterAll
    static void closeConnection() {
        MongoDBConfigurationTest.stopMongod();
    }
}
