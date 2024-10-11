package org.nabius.carservice.mapper;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.dto.CarDto;
import org.nabius.carservice.entity.Car;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarMapper {
    public Car mapToEntity(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        car.setEnginePower(carDto.getEnginePower());
        car.setTorque(carDto.getTorque());
        car.setFuelType(carDto.getFuelType());
        return car;
    }

    public CarDto mapToDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setEnginePower(car.getEnginePower());
        carDto.setTorque(car.getTorque());
        carDto.setFuelType(car.getFuelType());
        return carDto;
    }
}
