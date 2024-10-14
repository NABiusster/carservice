package org.nabius.carservice.repository;

import org.nabius.carservice.entity.Maintenance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends MongoRepository <Maintenance, Long> {

// Optional<Maintenance>  findById(Long id);

}
