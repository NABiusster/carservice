package org.nabius.carservice.service;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.api.DTO.OwnerDTO;
import org.nabius.carservice.entity.Owner;
import org.nabius.carservice.mapper.OwnerMapper;
import org.nabius.carservice.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        Owner owner = this.ownerMapper.mapToEntity(ownerDTO);
        return this.ownerMapper.mapToDto(this.ownerRepository.save(owner));
    }

    @Transactional
    public OwnerDTO updateOwner(Long ownerId, OwnerDTO ownerDTO) {
        return this.ownerRepository.findById(ownerId)
                .map(owner -> this.ownerMapper.update(ownerDTO, owner))
                .map(ownerMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
    }

    public void deleteOwner(Long ownerId) {
        Owner owner = this.ownerRepository.findById(ownerId)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
        this.ownerRepository.delete(owner);
    }

    public Optional<OwnerDTO> getOwner(Long ownerId) {
        return this.ownerRepository.findById(ownerId).map(ownerMapper::mapToDto);
    }

    public List<OwnerDTO> getAllOwners() {
        return this.ownerRepository.findAll().stream().map(ownerMapper::mapToDto).toList();

    }

    @Transactional
    public OwnerDTO updateOwnerPartially(Long ownerId, OwnerDTO ownerDTO) {
        return this.ownerRepository.findById(ownerId)
                .map(owner -> this.ownerMapper.partialUpdate(ownerDTO, owner))
                .map(ownerMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
    }
}
