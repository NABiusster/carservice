package org.nabius.carservice.service;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.CarDTO;
import org.nabius.carservice.entity.Car;
import org.nabius.carservice.entity.Owner;
import org.nabius.carservice.mapper.CarMapper;
import org.nabius.carservice.repository.CarRepository;
import org.nabius.carservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final OwnerRepository ownerRepository;


    public List<CarDTO> getCars(Integer minEnginePower, Integer maxEnginePower) {
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

    public List<CarDTO> getAllCars() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO findCarById(Long id) {
        return carMapper.mapToDTO(carRepository.findById(id).orElseThrow());
    }

    public List<CarDTO> findAllByEnginePowerBetween(int minEnginePower, int maxEnginePower) {
        return carRepository
                .findAllByEnginePowerBetween(minEnginePower, maxEnginePower)
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public List<CarDTO> findAllByEnginePowerGreaterThan(int minEnginePower) {
        return carRepository
                .findAllByEnginePowerGreaterThan(minEnginePower)
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public List<CarDTO> findAllByEnginePowerLessThan(int maxEnginePower) {
        return carRepository
                .findAllByEnginePowerLessThan(maxEnginePower)
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO addCar(String userName, CarDTO carDTO) {
        Owner owner = ownerRepository.findByUsername(userName).orElseThrow();
        Car car = carMapper.mapToEntity(carDTO);
        car.setOwner(owner);
        car = carRepository.save(car);
        return carMapper.mapToDTO(car);
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setModel(carDTO.getModel());
        car.setEnginePower(carDTO.getEnginePower());
        car.setTorque(carDTO.getTorque());
        car.setFuelType(carDTO.getFuelType());
        return carMapper.mapToDTO(car);

    }

    public CarDTO deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        carRepository.delete(car);
        return carMapper.mapToDTO(car);
    }
}
