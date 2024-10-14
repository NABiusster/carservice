package org.nabius.carservice.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document("maintenance")
public class Maintenance {
    @MongoId
    private Long id;
    private String name;
    private String description;
    private Double price;

}
