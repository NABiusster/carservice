package org.nabius.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.nabius.carservice.Enum.FuelType;

import java.time.LocalDateTime;

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
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn
    private Owner owner;
    private LocalDateTime lastMaintenanceTimestamp;

}
