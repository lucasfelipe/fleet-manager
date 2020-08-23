package com.lucas.fleetmanager.repositories;

import com.lucas.fleetmanager.config.MongoDBConfigurationTest;
import com.lucas.fleetmanager.entities.Places;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class PlacesRepositoryTest {

    static final String DISPLAY_PLACES_REPOSITORY_TEST = "Given an object places when save object using mongoDB template then check object saved.";
    public static final String BUS_STATION = "Bus Station";
    public static final String BUS_STATION_DESCRIPTION = "It's a place to take transportation by bus.";
    public static final String BUS_STATION_TYPE = "bus_station";

    static Places busStation = Places.builder()
            .name(BUS_STATION)
            .description(BUS_STATION_DESCRIPTION)
            .type(BUS_STATION_TYPE)
            .build();

    final PlacesRepository repository;

    @Autowired
    public PlacesRepositoryTest(PlacesRepository repository) {
        this.repository = repository;
    }

    @BeforeAll
    static void setupConnection() {
        MongoDBConfigurationTest.startMongoClient();
    }

    @BeforeEach
    void setup() {
        repository.save(busStation);
    }

    @DisplayName(DISPLAY_PLACES_REPOSITORY_TEST)
    @Test
    void testSavingPlacesUsingMongoDBCheckingObjectSavedProperly() {
        var gasStationFromDB = repository.findAll()
                .stream()
                .filter(element -> BUS_STATION.equals(element.getName()) && BUS_STATION_DESCRIPTION.equals(element.getDescription()))
                .findFirst()
                .orElse(null);

        assertNotNull(gasStationFromDB);
        busStation.setId(gasStationFromDB.getId());
    }

    @AfterEach
    void tearDown() {
        repository.delete(busStation);
    }

    @AfterAll
    static void closeConnection() {
        MongoDBConfigurationTest.stopMongod();
    }

}
