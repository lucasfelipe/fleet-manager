package com.lucas.fleetmanager.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.stereotype.Component;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Log4j2
@Component
public class MongoDBClientConfiguration extends AbstractMongoClientConfiguration {

    @Value("${mongodb.host}")
    private String mongoHost;

    @Value("${mongodb.port}")
    private String mongoPort;

    @Value("${mongodb.database}")
    private String databaseName;

    @Value("${mongodb.username}")
    private String mongoUser;

    @Value("${mongodb.password}")
    private String mongoPassword;


    @Override
    protected MongoClientSettings mongoClientSettings() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        return MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(getMongoURLString()))
                .codecRegistry(codecRegistry)
                .build();
    }

    private String getMongoURLString() {
        log.info("MONGO_HOST: {}", mongoHost);
        log.info("MONGO_DB: {}", databaseName);
        log.info("MONGO_PORT: {}", mongoPort);
        log.info("MONGO_USER: {}", mongoUser);
        log.info("MONGO_PASS: {}", mongoPassword);
        return "mongodb://"
                .concat(mongoUser).concat(":")
                .concat(mongoPassword).concat("@")
                .concat(mongoHost).concat(":")
                .concat(mongoPort).concat("/")
                .concat(databaseName);
    }

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }
}
