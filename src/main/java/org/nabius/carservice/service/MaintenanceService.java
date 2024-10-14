package org.nabius.carservice.service;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.MaintenanceDTO;
import org.nabius.carservice.entity.Maintenance;
import org.nabius.carservice.mapper.MaintenanceMapper;
import org.nabius.carservice.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    public List<MaintenanceDTO> getAllMaintenance(){
        return maintenanceRepository
                .findAll()
                .stream()
                .map(maintenanceMapper::mapToDTO)
                .toList();
    }

    public MaintenanceDTO createMaintenance(MaintenanceDTO maintenanceDTO){
        Maintenance maintenance = maintenanceMapper.mapToEntity(maintenanceDTO);
        return maintenanceMapper.mapToDTO(maintenanceRepository.save(maintenance));
    }
    public MaintenanceDTO updateMaintenance(MaintenanceDTO maintenanceDTO){
        Maintenance maintenance = maintenanceMapper.mapToEntity(maintenanceDTO);
        return maintenanceMapper.mapToDTO(maintenanceRepository.save(maintenance));
    }

    public MaintenanceDTO deleteMaintenance(Long maintenanceId){
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElseThrow();
        maintenanceRepository.delete(maintenance);
        return maintenanceMapper.mapToDTO(maintenance);
    }
    public MaintenanceDTO getMaintenanceById(Long maintenanceId){
         return  maintenanceMapper.mapToDTO(maintenanceRepository.findById(maintenanceId).orElseThrow());
    }

    public List<MaintenanceDTO> getAllMaintenanceFindB(){
        return maintenanceRepository
                .findAll()
                .stream()
                .map(maintenanceMapper::mapToDTO)
                .toList();
    }



}
