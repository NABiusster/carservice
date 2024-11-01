package org.nabius.carservice.mapper;

import org.mapstruct.*;
import org.nabius.carservice.api.DTO.CarDTO;
import org.nabius.carservice.entity.Car;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarMapper {
    Car mapToEntity(CarDTO carDTO);

    @Mapping(target = "owner.cars", ignore = true)
    CarDTO mapToDto(Car car);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Car partialUpdate(CarDTO carDTO, @MappingTarget Car car);

    Car update(CarDTO carDTO, @MappingTarget Car car);

}