package org.nabius.carservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.CarDTO;
import org.nabius.carservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    //    GET /cars - повертає список усіх авто
    @GetMapping()
    public ResponseEntity<List<CarDTO>> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ) {
       return ResponseEntity.ok(carService.getCars(minEnginePower, maxEnginePower));
    }

    //    GET /cars/{id} - повертає конкретне авто по ІД
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findCarById(id));
    }

    //    POST /cars - створює нове авто
    @PostMapping()
    public CarDTO addCar(@RequestParam String userName, @RequestBody @Valid CarDTO carDTO) {
        return carService.addCar(userName, carDTO);
    }

    //    PUT /cars/{id} - оновлює дані про авто по ІД
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable(name = "id") Long id, @RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok(carService.updateCar(id,carDTO));
    }

    //    DELETE /cars/{id} - видалити авто по ІД
    @DeleteMapping("/{id}")
    public ResponseEntity<CarDTO> deleteCar(@PathVariable(name = "id") Long carId) {
        return ResponseEntity.ok(carService.deleteCar(carId));
    }
}
