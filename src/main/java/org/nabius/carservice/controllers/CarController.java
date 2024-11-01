package org.nabius.carservice.controllers;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.api.DTO.CarDTO;
import org.nabius.carservice.api.DTO.MaintenanceDTO;
import org.nabius.carservice.api.controllers.CarsApi;
import org.nabius.carservice.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController implements CarsApi {

    private final CarService carservice;

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<CarDTO> addCar(String username, CarDTO carDTO) {
        return ResponseEntity.ok(carservice.addCar(username, carDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<MaintenanceDTO> addMaintenance(Long carId, MaintenanceDTO maintenanceDTO) {
        return ResponseEntity.ok(carservice.addMaintenance(carId, maintenanceDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<Void> deleteCar(Long id) {
        carservice.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<Void> deleteMaintenance(Long id) {
        carservice.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<CarDTO> getCar(Long carId) {
        return ResponseEntity.of(carservice.findCar(carId));
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<List<CarDTO>> getCars() {
        return ResponseEntity.ok(carservice.findCars());
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<MaintenanceDTO> getMaintenance(Long id) {
        return ResponseEntity.of(carservice.findMaintenance(id));
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<List<MaintenanceDTO>> getMaintenances(Long carId) {
        return ResponseEntity.ok(carservice.findMaintenances(carId));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<CarDTO> modifyCar(Long id, CarDTO carDTO) {
        return ResponseEntity.of(carservice.modifyCar(id, carDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<CarDTO> modifyCarPartially(Long id, CarDTO carDTO) {
        return ResponseEntity.of(carservice.modifyCarPartially(id, carDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<MaintenanceDTO> updateMaintenance(Long id, MaintenanceDTO maintenanceDTO) {
        return ResponseEntity.of(carservice.updateMaintenance(id, maintenanceDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<MaintenanceDTO> updateMaintenancePartially(Long id, MaintenanceDTO maintenanceDTO) {
        return ResponseEntity.of(carservice.updateMaintenancePartially(id, maintenanceDTO));
    }
}
