package org.nabius.carservice.service;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.api.DTO.CarDTO;
import org.nabius.carservice.api.DTO.MaintenanceDTO;
import org.nabius.carservice.entity.Car;
import org.nabius.carservice.entity.Maintenance;
import org.nabius.carservice.entity.Owner;
import org.nabius.carservice.mapper.CarMapper;
import org.nabius.carservice.mapper.MaintenanceMapper;
import org.nabius.carservice.repository.CarRepository;
import org.nabius.carservice.repository.MaintenanceRepository;
import org.nabius.carservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarMapper carMapper;
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;


    public CarDTO addCar(String username, CarDTO carDTO) {
        Owner owner = this.ownerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("Owner not found")
        );
        Car car = this.carMapper.mapToEntity(carDTO);
        car.setOwner(owner);
        return this.carMapper.mapToDto(this.carRepository.save(car));
    }

    public MaintenanceDTO addMaintenance(Long carId, MaintenanceDTO maintenanceDTO) {
        Car car = this.carRepository.findById(carId).orElseThrow(
                () -> new IllegalArgumentException("Car with id " + carId + " does not exist")
        );
        Maintenance maintenance = this.maintenanceMapper.mapToEntity(maintenanceDTO);
        maintenance.setCarId(carId);
        car.setLastMaintenanceTimestamp(Instant.from(LocalDateTime.now()));
        this.carRepository.save(car);
        return this.maintenanceMapper.mapToDto(this.maintenanceRepository.save(maintenance));
    }

    public void deleteCar(Long carId) {
        Car car = this.carRepository.findById(carId).orElseThrow(
                () -> new IllegalArgumentException("Car with id " + carId + " does not exist")
        );
        this.carRepository.delete(car);
    }

    public void deleteMaintenance(Long maintenanceId) {
        Maintenance maintenance = this.maintenanceRepository.findById(maintenanceId).orElseThrow(
                () -> new IllegalArgumentException("Maintenance with id " + maintenanceId + " does not exist")
        );
        this.maintenanceRepository.delete(maintenance);
    }

    public Optional<CarDTO> findCar(Long carId) {
        return this.carRepository.findById(carId).map(this.carMapper::mapToDto);
    }

    public List<CarDTO> findCars() {
        return this.carRepository.findAll().
                stream().
                map(this.carMapper::mapToDto)
                .toList();
    }

    public Optional<MaintenanceDTO> findMaintenance(Long maintenanceId) {
        return this.maintenanceRepository.findById(maintenanceId)
                .map(this.maintenanceMapper::mapToDto);
    }

    public List<MaintenanceDTO> findMaintenances(Long carId) {
        this.carRepository.findById(carId).orElseThrow(
                () -> new IllegalArgumentException("Car with id " + carId + " does not exist")
        );
        return maintenanceRepository.findAllByCarId(carId)
                .stream()
                .map(this.maintenanceMapper::mapToDto)
                .toList();
    }

    @Transactional
    public Optional<CarDTO> modifyCar(Long carId, CarDTO carDTO) {
        return this.carRepository.findById(carId)
                .map(car -> this.carMapper.update(carDTO, car))
                .map(this.carMapper::mapToDto);
    }

    @Transactional
    public Optional<CarDTO> modifyCarPartially(Long carId, CarDTO carDTO) {
        return this.carRepository.findById(carId)
                .map(car -> this.carMapper.partialUpdate(carDTO, car))
                .map(carMapper::mapToDto);
    }

    @Transactional
    public Optional<MaintenanceDTO> updateMaintenance(Long maintenanceId, MaintenanceDTO maintenanceDTO) {
        return this.maintenanceRepository.findById(maintenanceId)
                .map(maintenance -> this.maintenanceMapper.update(maintenanceDTO, maintenance))
                .map(this.maintenanceMapper::mapToDto);
    }

    @Transactional
    public Optional<MaintenanceDTO> updateMaintenancePartially(Long maintenanceId, MaintenanceDTO maintenanceDTO) {
        return this.maintenanceRepository.findById(maintenanceId)
                .map(maintenance -> this.maintenanceMapper.partialUpdate(maintenanceDTO, maintenance))
                .map(this.maintenanceMapper::mapToDto);
    }
}