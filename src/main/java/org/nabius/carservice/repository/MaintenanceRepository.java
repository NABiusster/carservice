package org.nabius.carservice.repository;

import org.nabius.carservice.entity.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends MongoRepository<Maintenance, Long> {

    List<Maintenance> findAllByCarId(Long id);
}
