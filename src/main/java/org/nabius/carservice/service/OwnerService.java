package org.nabius.carservice.service;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.OwnerDTO;
import org.nabius.carservice.entity.Owner;
import org.nabius.carservice.mapper.OwnerMapper;
import org.nabius.carservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public List<OwnerDTO> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(ownerMapper::mapToDTO)
                .toList();
    }
    public OwnerDTO getOwnerById(Long id) {
        return ownerMapper.mapToDTO(ownerRepository.findById(id).orElseThrow());
    }
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.mapToEntity(ownerDTO);
        return ownerMapper.mapToDTO(ownerRepository.save(owner));
    }
    public OwnerDTO updateOwner(Long id, OwnerDTO ownerDTO) {
        Owner owner = ownerRepository.findById(id).orElseThrow();
        owner.setEmail(ownerDTO.getEmail());
        owner.setUsername(ownerDTO.getUsername());
        return ownerMapper.mapToDTO(ownerRepository.save(owner));
    }
    public OwnerDTO deleteOwner(Long id) {
        Owner owner = ownerRepository.findById(id).orElseThrow();
        ownerRepository.delete(owner);
        return ownerMapper.mapToDTO(owner);
    }
}
