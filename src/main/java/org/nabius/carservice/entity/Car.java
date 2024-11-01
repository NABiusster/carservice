package org.nabius.carservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nabius.carservice.api.DTO.CarDTO;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "engine_power")
    private Integer enginePower;

    @Column(name = "torque")
    private Double torque;


    @Column(name = "last_maintenance_timestamp")
    private Instant lastMaintenanceTimestamp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Owner owner;

    @Enumerated
    @Column(name = "fuel_type")
    private CarDTO.FuelTypeEnum fuelType;

}