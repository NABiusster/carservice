package org.nabius.carservice.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.nabius.carservice.Enum.FuelType;

import java.util.List;

@Data
public class CarDTO {
    private Long id;
    @NotBlank (message = "must be not blank")
    private String model;
    @Min(value = 1, message = "must be greater than 1")
    @Max(value = 4000, message = "must be less than 4000")
    private int enginePower;
    @Min(value = 0, message = "must greater than 0")
    @Max(value = 100, message = "must less than 100")
    private Double torque;
    private FuelType fuelType;
}
