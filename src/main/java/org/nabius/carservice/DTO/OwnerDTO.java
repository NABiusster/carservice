package org.nabius.carservice.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OwnerDTO {
    @NotBlank
    private String username;
    @NotBlank
    @Email
    private String email;
    private List<CarDTO> cars = new ArrayList<>();
}