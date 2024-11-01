package org.nabius.carservice.mapper;

import org.mapstruct.*;
import org.nabius.carservice.api.DTO.OwnerDTO;
import org.nabius.carservice.entity.Owner;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnerMapper {
    org.nabius.carservice.entity.Owner mapToEntity(OwnerDTO ownerDTO);

    @Mapping(target = "cars.owner" , ignore = true)
    OwnerDTO mapToDto(org.nabius.carservice.entity.Owner owner);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Owner partialUpdate(OwnerDTO ownerDTO, @MappingTarget Owner owner);

    Owner update(OwnerDTO ownerDTO, @MappingTarget Owner owner);

}