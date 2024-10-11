package org.nabius.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.nabius.carservice.Enum.FuelType;

@Entity
@Table(name = "cars")
@Data

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @Column(name = "engine_power")
    private int enginePower;
    private Double torque;
    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

}
