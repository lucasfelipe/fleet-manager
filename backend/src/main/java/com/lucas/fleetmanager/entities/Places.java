package com.lucas.fleetmanager.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "places")
public class Places {

    private String id;
    private String name;
    private String description;
    private String type;

}
