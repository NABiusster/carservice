package org.nabius.carservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.OwnerDTO;
import org.nabius.carservice.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping()
    public ResponseEntity<List<OwnerDTO>> getOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @PostMapping()
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody @Valid OwnerDTO ownerDTO) {
        return ResponseEntity.ok(ownerService.createOwner(ownerDTO));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody @Valid OwnerDTO ownerDTO) {
        return ResponseEntity.ok(ownerService.updateOwner(id, ownerDTO));
    }

    @DeleteMapping("/{id})")
    public ResponseEntity<OwnerDTO> deleteOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.deleteOwner(id));
    }
}