package org.nabius.carservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.dto.CarDto;
import org.nabius.carservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    //    GET /cars - повертає список усіх авто
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ) {
       return ResponseEntity.ok(carService.getCars(minEnginePower, maxEnginePower));
    }

    //    GET /cars/{id} - повертає конкретне авто по ІД
    @GetMapping("/cars/{id}")
    public ResponseEntity<CarDto> getCar(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findCarById(id));
    }

    //    POST /cars - створює нове авто
    @PostMapping("/cars")
    public CarDto addCar(@RequestBody @Valid CarDto carDto) {
        return carService.addCar(carDto);
    }

    //    PUT /cars/{id} - оновлює дані про авто по ІД
    @Transactional
    @PutMapping("/cars/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable(name = "id") Long id, @RequestBody @Valid CarDto carDto) {
        return ResponseEntity.ok(carService.updateCar(id,carDto));
    }

    //    DELETE /cars/{id} - видалити авто по ІД
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<CarDto> deleteCar(@PathVariable(name = "id") Long carId) {
        return ResponseEntity.ok(carService.deleteCar(carId));
    }
}
