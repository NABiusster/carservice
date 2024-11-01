package org.nabius.carservice.mapper;

import org.mapstruct.*;
import org.nabius.carservice.api.DTO.MaintenanceDTO;
import org.nabius.carservice.entity.Maintenance;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MaintenanceMapper {
    Maintenance mapToEntity(MaintenanceDTO maintenanceDTO);

    MaintenanceDTO mapToDto(Maintenance maintenance);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Maintenance partialUpdate(MaintenanceDTO maintenanceDTO, @MappingTarget Maintenance maintenance);
    Maintenance update(MaintenanceDTO maintenanceDTO, @MappingTarget Maintenance maintenance);
}