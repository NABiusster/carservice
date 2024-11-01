package org.nabius.carservice.controllers;

import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.nabius.carservice.api.DTO.OwnerDTO;
import org.nabius.carservice.api.controllers.OwnersApi;
import org.nabius.carservice.service.OwnerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/owners")
public class OwnerController implements OwnersApi {

    private final OwnerService ownerService;

    @Override
    @RolesAllowed({"ADMIN"})
    public ResponseEntity<OwnerDTO> createOwner(OwnerDTO ownerDTO) {
        return ResponseEntity.ok(this.ownerService.createOwner(ownerDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<Void> deleteOwner(Long id) {
        this.ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<OwnerDTO> getOwnerById(Long id) {
        return ResponseEntity.of(this.ownerService.getOwner(id));
    }

    @RolesAllowed({"USER", "ADMIN"})
    @Override
    public ResponseEntity<List<OwnerDTO>> getOwners() {
        return ResponseEntity.ok(this.ownerService.getAllOwners());
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<OwnerDTO> updateOwner(Long id, OwnerDTO ownerDTO) {
        return ResponseEntity.ok(this.ownerService.updateOwner(id, ownerDTO));
    }

    @RolesAllowed({"ADMIN"})
    @Override
    public ResponseEntity<OwnerDTO> updateOwnerPartially(Long id, OwnerDTO ownerDTO) {
        return ResponseEntity.ok(this.ownerService.updateOwnerPartially(id, ownerDTO));
    }
}