package org.nabius.carservice.Controllers;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.Entity.Car;
import org.nabius.carservice.Repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarRepository carRepository;

    //    GET /cars - повертає список усіх авто
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCars(
            @RequestParam(name = "minEnginePower", required = false) Integer minEnginePower,
            @RequestParam(name = "maxEnginePower", required = false) Integer maxEnginePower
    ) {
        if (minEnginePower != null && maxEnginePower != null) {
            return ResponseEntity.ok(carRepository.findAllByEnginePowerBetween(minEnginePower, maxEnginePower));
        } else if (minEnginePower != null) {
            return ResponseEntity.ok(carRepository.findAllByEnginePowerGreaterThan(minEnginePower));
        } else if (maxEnginePower != null) {
            return ResponseEntity.ok(carRepository.findAllByEnginePowerLessThan(maxEnginePower));
        } else {
            return ResponseEntity.ok(carRepository.findAll());
        }
    }

    //    GET /cars/{id} - повертає конкретне авто по ІД
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        return ResponseEntity.of(carRepository.findById(id));
    }

    //    POST /cars - створює нове авто
    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    //    PUT /cars/{id} - оновлює дані про авто по ІД
    @Transactional
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") Integer carId, @RequestBody Car car) {
        return ResponseEntity.of(
                carRepository
                        .findById(carId)
                        .map(oldCar -> {
                            oldCar.setModel(car.getModel());
                            oldCar.setEnginePower(car.getEnginePower());
                            return oldCar;
                        })
        );
    }

    //    DELETE /cars/{id} - видалити авто по ІД
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable(name = "id") Integer carId) {
        carRepository.deleteById(carId);
        return ResponseEntity.ok().build();
    }
}
