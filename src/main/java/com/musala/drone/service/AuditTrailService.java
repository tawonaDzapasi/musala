package com.musala.drone.service;

import com.musala.drone.entity.AuditTrail;

import java.util.List;

public interface AuditTrailService {

    public AuditTrail save(AuditTrail auditTrail);
    public AuditTrail getAuditTrailByDroneId(Long id);

    public List<AuditTrail> getAll();
}
