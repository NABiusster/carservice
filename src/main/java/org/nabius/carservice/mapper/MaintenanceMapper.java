package org.nabius.carservice.mapper;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.MaintenanceDTO;
import org.nabius.carservice.entity.Maintenance;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MaintenanceMapper {
    public Maintenance mapToEntity(MaintenanceDTO maintenanceDTO) {
        Maintenance maintenance = new Maintenance();
        maintenance.setName(maintenance.getName());
        maintenance.setDescription(maintenance.getDescription());
        maintenance.setPrice(maintenance.getPrice());
        return maintenance;
    }

    public MaintenanceDTO mapToDTO(Maintenance maintenance) {
        MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
        maintenanceDTO.setName(maintenance.getName());
        maintenanceDTO.setDescription(maintenance.getDescription());
        maintenanceDTO.setPrice(maintenance.getPrice());
        return maintenanceDTO;
    }
}
