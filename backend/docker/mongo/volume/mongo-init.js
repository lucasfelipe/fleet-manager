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
            required: ['name', 'description'],
            properties: {
                name: {
                    bsonType: 'string',
                    description: 'must be a string and is required'
                },
                description: {
                    bsonType: 'string',
                    description: 'must be a string and is required'
                }
            }
        }
    }
});

db.createCollection("trucks", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: [ "license_plate", "driver", "locations" ],
            properties: {
                license_plate: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                driver: {
                    bsonType: "string",
                    description: "must be a string and is required"
                },
                locations: {
                    bsonType: "array",
                    required: [ "lat", "long" ],
                    properties: {
                        lat: {
                            bsonType: "double",
                            description: "must be a double and is required"
                        },
                        long: {
                            bsonType: "double",
                            description: "must be a double and is required"
                        }
                    }
                }
            }
        }
    }
});

// Loading POI types
const typesPOI = [
    {
        'name': 'Gas station',
        'description': 'Place for buy snacks and adding fuel'
    },
    {
        'name': 'Hotels',
        'description': 'Place for resting'
    },
    {
        'name': 'Restaurants',
        'description': 'Place for lunch or dinner'
    }
];

db.places.insertMany(typesPOI);

// Loading Trucks
const trucks = [
    {
        license_plate: 'VW202001',
        driver: 'Alfonse Henriquez',
        locations: [
            //TODO: Adding lat, long array based on the google maps
        ]
    },
    {
        license_plate: 'VW202002',
        driver: 'Hendrickson Fleck',
        locations: [
            //TODO: Adding lat, long array based on the google maps
        ]
    },
    {
        license_plate: 'VW202003',
        driver: 'George Phillip',
        locations: [
            //TODO: Adding lat, long array based on the google maps
        ]
    }
];

db.trucks.insertMany(trucks);

