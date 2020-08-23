package com.lucas.fleetmanager.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.extern.log4j.Log4j2;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.io.IOException;
import java.util.Objects;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Log4j2
public class MongoDBConfigurationTest {

    private static final String MONGO_DB_URL = "localhost";
    private static final int MONGO_DB_PORT = 27017;
    private static final String MONGO_DB_NAME = "embeded_db";

    private static MongodExecutable mongodExe;
    private static MongodProcess mongod;
    private static MongoClient mongo;

    public static void startMongoClient() {
        startMongod();
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        mongo = MongoClients.create(MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(getMongoURLString()))
                .codecRegistry(codecRegistry)
                .build());
    }

    public static void startMongod() {
        try {
            if (Objects.isNull(mongod)) {
                MongodStarter starter = MongodStarter.getDefaultInstance();
                IMongodConfig mongodConfig;
                mongodConfig = new MongodConfigBuilder().version(Version.Main.V3_6)
                        .net(new Net(MONGO_DB_URL, MONGO_DB_PORT, Network.localhostIsIPv6())).build();
                mongodExe = starter.prepare(mongodConfig);
            }
            mongod = mongodExe.start();
        } catch (IOException e) {
            log.error("Error when trying to create embedded mongodb: {}", e.getMessage(), e);
        }

    }

    public static void stopMongod() {
        if (Objects.nonNull(mongod)) {
            mongod.stop();
            mongodExe.stop();
            mongo.close();
            mongo = null;
        }
    }

    private static String getMongoURLString() {
        return "mongodb://"
                .concat(MONGO_DB_URL).concat(":")
                .concat(String.valueOf(MONGO_DB_PORT)).concat("/")
                .concat(MONGO_DB_NAME);
    }

}
