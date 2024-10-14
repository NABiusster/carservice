package org.nabius.carservice.controllers;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.MaintenanceDTO;
import org.nabius.carservice.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenances")
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @GetMapping()
    public ResponseEntity<List<MaintenanceDTO>> getAllMaintenances() {
        return ResponseEntity.ok(maintenanceService.getAllMaintenance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> getMaintenanceById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getMaintenanceById(id));

    }

    @PostMapping()
    public ResponseEntity<MaintenanceDTO> createMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        return ResponseEntity.ok(maintenanceService.createMaintenance(maintenanceDTO));
    }

    @Transactional
    @PutMapping()
    public ResponseEntity<MaintenanceDTO> updateMaintenance(@RequestBody MaintenanceDTO maintenanceDTO) {
        return ResponseEntity.ok(maintenanceService.updateMaintenance(maintenanceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MaintenanceDTO> deleteMaintenance(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.deleteMaintenance(id));
    }

}
