package org.nabius.carservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Data
@Document("maintenances")
public class Maintenance {
    @MongoId
    private Long id;

    private Long carId;

    private String name;

    private String description;

    private Double price;

    private Instant timestamp;

}