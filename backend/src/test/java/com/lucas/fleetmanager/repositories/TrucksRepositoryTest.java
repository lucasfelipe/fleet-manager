package com.lucas.fleetmanager.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class TrucksRepositoryTest {

    public static final String DISPLAY_GET_TRUCK_BY_LICENSE_TEST = "Given a license plate when try to find a truck by repo then check if the license is the same";
    public static final String LICENSE_PLATE = "VW202001";
    final TrucksRepository repository;

    @Autowired
    public TrucksRepositoryTest(TrucksRepository repository) {
        this.repository = repository;
    }

    @DisplayName(DISPLAY_GET_TRUCK_BY_LICENSE_TEST)
    @Test
    void testFindTruckByLicensePlateShouldReturnObject() {
        var truck = repository.findByLicensePlate(LICENSE_PLATE);
        assertNotNull(truck);
        assertEquals(LICENSE_PLATE, truck.getLicensePlate());
    }
}
