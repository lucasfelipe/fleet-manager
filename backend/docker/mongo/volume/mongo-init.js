db.createUser({
    user: "userdb",
    pwd: "UserPwd1234",
    roles: [
        {
            role: "readWrite",
            db: "fleet-manager"
        }
    ]
});


// Create collections
db.createCollection('places', {
    validator: {
        $jsonSchema: {
            bsonType: 'object',
            required: ['name', 'description', 'type'],
            properties: {
                name: {
                    bsonType: 'string',
                    description: 'must be a string and is required'
                },
                description: {
                    bsonType: 'string',
                    description: 'must be a string and is required'
                },
                type: {
                    bsonType: 'string',
                    description: 'must be a string and is required, it represents google type.'
                }
            }
        }
    }
});

db.createCollection("trucks", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: [ "licensePlate", "driver", "locations" ],
            properties: {
                licensePlate: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                driver: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                locations: {
                    bsonType: "array"
                }
            }
        }
    }
});

// Loading POI types
const typesPOI = [
    {
        'name': 'Gas station',
        'description': 'Place for buy snacks and adding fuel',
        'type': 'gas_station'
    },
    {
        'name': 'Hotels',
        'description': 'Place for resting',
        'type': 'hotel'
    },
    {
        'name': 'Restaurants',
        'description': 'Place for lunch or dinner',
        'type': 'restaurant'
    }
];

db.places.insertMany(typesPOI);

// Loading Trucks
const trucks = [
    {
        licensePlate: 'VW202001',
        driver: 'Alfonse Henriquez',
        locations: [
            { type: "Point", coordinates: [ 38.775721, -9.160135 ] },
            { type: "Point", coordinates: [ 38.776536, -9.159305 ] },
            { type: "Point", coordinates: [ 38.776862, -9.158844 ] },
            { type: "Point", coordinates: [ 38.777305, -9.158393 ] },
            { type: "Point", coordinates: [ 38.777790, -9.157942 ] },
            { type: "Point", coordinates: [ 38.778258, -9.157524 ] },
            { type: "Point", coordinates: [ 38.778659, -9.157234 ] },
            { type: "Point", coordinates: [ 38.779554, -9.156451 ] },
            { type: "Point", coordinates: [ 38.780800, -9.155550 ] },
            { type: "Point", coordinates: [ 38.781143, -9.154702 ] },
            { type: "Point", coordinates: [ 38.780825, -9.154026 ] }
        ]
    },
    {
        licensePlate: 'VW202002',
        driver: 'Hendrickson Fleck',
        locations: [
            { type: "Point", coordinates: [ 38.739681, -9.151685 ] },
            { type: "Point", coordinates: [ 38.740878, -9.148896 ] },
            { type: "Point", coordinates: [ 38.741397, -9.145860 ] },
            { type: "Point", coordinates: [ 38.741958, -9.142620 ] },
            { type: "Point", coordinates: [ 38.742393, -9.139723 ] },
            { type: "Point", coordinates: [ 38.742435, -9.137406 ] },
            { type: "Point", coordinates: [ 38.742217, -9.134284 ] },
            { type: "Point", coordinates: [ 38.741964, -9.131146 ] },
            { type: "Point", coordinates: [ 38.741060, -9.128593 ] },
            { type: "Point", coordinates: [ 38.739880, -9.127745 ] },
            { type: "Point", coordinates: [ 38.738240, -9.127166 ] }
        ]
    },
    {
        licensePlate: 'VW202003',
        driver: 'George Phillip',
        locations: [
            { type: "Point", coordinates: [ 50.344608, 6.963438 ] },
            { type: "Point", coordinates: [ 50.345696, 6.966147 ] },
            { type: "Point", coordinates: [ 50.347065, 6.969602 ] },
            { type: "Point", coordinates: [ 50.347839, 6.971555 ] },
            { type: "Point", coordinates: [ 50.348880, 6.974269 ] },
            { type: "Point", coordinates: [ 50.349845, 6.976586 ] },
            { type: "Point", coordinates: [ 50.350783, 6.979021 ] },
            { type: "Point", coordinates: [ 50.351618, 6.981639 ] },
            { type: "Point", coordinates: [ 50.352049, 6.984010 ] },
            { type: "Point", coordinates: [ 50.352590, 6.986832 ] },
            { type: "Point", coordinates: [ 50.353261, 6.990287 ] }
        ]
    }
];

db.trucks.insertMany(trucks);

