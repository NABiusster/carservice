package org.nabius.carservice.repository;

import org.nabius.carservice.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByEnginePowerBetween(int minEnginePower, int maxEnginePower);
    List<Car> findAllByEnginePowerGreaterThan(int minEnginePower);
    List<Car> findAllByEnginePowerLessThan(int maxEnginePower);
}
