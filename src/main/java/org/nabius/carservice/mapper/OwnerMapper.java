package org.nabius.carservice.mapper;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.OwnerDTO;
import org.nabius.carservice.entity.Owner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final CarMapper carMapper;

    public Owner mapToEntity(OwnerDTO ownerDTO) {
        Owner owner = new Owner();
        owner.setUsername(ownerDTO.getUsername());
        owner.setEmail(ownerDTO.getEmail());
        owner.setCars(
                ownerDTO.getCars()
                        .stream()
                        .map(carMapper::mapToEntity)
                        .toList()
        );
        return owner;
    }

    public OwnerDTO mapToDTO(Owner owner) {
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setUsername(owner.getUsername());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setCars(
                owner.getCars()
                        .stream()
                        .map(carMapper::mapToDTO)
                        .toList()
        );
        return ownerDTO;
    }
}
