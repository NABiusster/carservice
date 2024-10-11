package org.nabius.carservice.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.dto.CarDto;
import org.nabius.carservice.entity.Car;
import org.nabius.carservice.mapper.CarMapper;
import org.nabius.carservice.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;


    public List<CarDto> getCars(Integer minEnginePower, Integer maxEnginePower) {
        if (minEnginePower != null && maxEnginePower != null) {
            return findAllByEnginePowerBetween(minEnginePower, maxEnginePower);
        } else if (minEnginePower != null) {
            return findAllByEnginePowerGreaterThan(minEnginePower);
        } else if (maxEnginePower != null) {
            return findAllByEnginePowerLessThan(maxEnginePower);
        } else {
            return getAllCars();
        }
    }

    public List<CarDto> getAllCars() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDto)
                .toList();
    }

    public CarDto findCarById(Long id) {
        return carMapper.mapToDto(carRepository.findById(id).orElseThrow());
    }

    public List<CarDto> findAllByEnginePowerBetween(int minEnginePower, int maxEnginePower) {
        return carRepository
                .findAllByEnginePowerBetween(minEnginePower, maxEnginePower)
                .stream()
                .map(carMapper::mapToDto)
                .toList();
    }

    public List<CarDto> findAllByEnginePowerGreaterThan(int minEnginePower) {
        return carRepository
                .findAllByEnginePowerGreaterThan(minEnginePower)
                .stream()
                .map(carMapper::mapToDto)
                .toList();
    }

    public List<CarDto> findAllByEnginePowerLessThan(int maxEnginePower) {
        return carRepository
                .findAllByEnginePowerLessThan(maxEnginePower)
                .stream()
                .map(carMapper::mapToDto)
                .toList();
    }

    public CarDto addCar(CarDto carDto) {
        Car car = carMapper.mapToEntity(carDto);
        car = carRepository.save(car);
        return carMapper.mapToDto(car);
    }

    public CarDto updateCar(Long id, CarDto carDto) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setModel(carDto.getModel());
        car.setEnginePower(carDto.getEnginePower());
        car.setTorque(carDto.getTorque());
        car.setFuelType(carDto.getFuelType());
        return carMapper.mapToDto(car);

    }

    public CarDto deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        carRepository.delete(car);
        return carMapper.mapToDto(car);
    }
}
