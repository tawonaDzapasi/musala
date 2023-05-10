package com.musala.drone.repository;

import com.musala.drone.entity.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail,Long> {
    public AuditTrail findByDroneId(Long id);
}
