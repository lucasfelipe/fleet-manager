package com.lucas.fleetmanager.consumer.dto;

import lombok.Data;

import java.io.Serializable;

/*"business_status": "OPERATIONAL",
        "geometry": {
        "location": {
        "lat": 38.7563058,
        "lng": -9.1761924
        },
        "viewport": {
        "northeast": {
        "lat": 38.75765463029149,
        "lng": -9.174837769708498
        },
        "southwest": {
        "lat": 38.75495666970849,
        "lng": -9.177535730291503
        }
        }
        },
        "icon": "https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png",
        "name": "Galp Energia, SGPS S.A.",
        "opening_hours": {
        "open_now": false
        },
        "photos": [
        {
        "height": 2708,
        "html_attributions": [
        "<a href=\"https://maps.google.com/maps/contrib/104695929614523348689\">Francisco Vargas</a>"
        ],
        "photo_reference": "CmRaAAAAEvC2UDBqbMW-Z2_NWJyQLEUZJcF81KQ7fN0DcSeApVMK5gn8uTw1O0e_4N_IbQRY4y3NnLpc4hHqNVlaGhDN-aMsjgrLUhDbIgJrf7c_XzMlevp-EbiBGChzriWYQnSHEhBR9HsPc0WEyK1yaAQ226i8GhSIFtT7jVRCD35wnupoAsBrb8JQyA",
        "width": 3814
        }
        ],
        "place_id": "ChIJ_SCZndkyGQ0RXvl-BevB6WE",
        "plus_code": {
        "compound_code": "QR4F+GG Lisbon, Portugal",
        "global_code": "8CCGQR4F+GG"
        },
        "rating": 3,
        "reference": "ChIJ_SCZndkyGQ0RXvl-BevB6WE",
        "scope": "GOOGLE",
        "types": [
        "gas_station",
        "point_of_interest",
        "establishment"
        ],
        "user_ratings_total": 25,
        "vicinity": "Rua Tomás da Fonseca, Torres de Lisboa, Torre Galp - 5º Andar, Lisboa"*/

@Data
public class GooglePlaceDTO implements Serializable {

    String vicinity;
    GooglePlaceGeometryDTO geometry;
    String name;

}
