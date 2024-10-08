package org.nabius.carservice.Controllers;

import lombok.AllArgsConstructor;
import org.nabius.carservice.Config.prorerties.DataReferenceProperties;
import org.nabius.carservice.Config.prorerties.FuelType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class DataReferenceController {
//    @Value("${data-reference.engine-types}")
//    private List<String> engineTypes;

    private final DataReferenceProperties dataReferenceProperties;

//    @GetMapping("/engine-types")
//    public List<String> getEngineTypes() {
//        return engineTypes;
//    }
//
//    @GetMapping("/fuel-types")
//    public List<FuelType> getFuelTypes() {
//        return dataReferenceProperties.getFuelTypes();
//    }
//
//    @GetMapping("/fuel-types/{fuelName}")
//    public FuelType getFuelType(@PathVariable String fuelName) {
//        return dataReferenceProperties.getFuelTypes().stream()
//                .filter(fuelType -> fuelName.equals(fuelType.getName()))
//                .findFirst()
//                .orElse(null);
//    }

}
