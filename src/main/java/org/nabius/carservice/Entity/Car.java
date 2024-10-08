package org.nabius.carservice.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table (name = "cars")
@Data

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String model;
    private Integer enginePower;

}
