package org.nabius.carservice.Repository;

import org.nabius.carservice.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByEnginePowerBetween(Integer minEnginePower, Integer maxEnginePower);
    List<Car> findAllByEnginePowerGreaterThan(Integer minEnginePower);
    List<Car> findAllByEnginePowerLessThan(Integer maxEnginePower);
}
