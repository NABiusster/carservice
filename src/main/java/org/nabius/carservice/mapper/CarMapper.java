package org.nabius.carservice.mapper;

import lombok.RequiredArgsConstructor;
import org.nabius.carservice.DTO.CarDTO;
import org.nabius.carservice.entity.Car;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CarMapper {
    public Car mapToEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setModel(carDTO.getModel());
        car.setEnginePower(carDTO.getEnginePower());
        car.setTorque(carDTO.getTorque());
        car.setFuelType(carDTO.getFuelType());
        return car;
    }

    public CarDTO mapToDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setModel(car.getModel());
        carDTO.setEnginePower(car.getEnginePower());
        carDTO.setTorque(car.getTorque());
        carDTO.setFuelType(car.getFuelType());
        return carDTO;
    }
}
