package org.nabius.carservice.Config.prorerties;

import lombok.Data;

import java.util.List;

@Data
public class FuelType {
    String name;
    List<String> brand;
}
