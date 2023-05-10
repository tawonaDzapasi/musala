package com.musala.drone.service.impl;

import com.musala.drone.entity.AuditTrail;
import com.musala.drone.repository.AuditTrailRepository;
import com.musala.drone.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuditTrailServiceImpl implements AuditTrailService {
    @Autowired
    private AuditTrailRepository auditTrailRepository;

    @Override
    public AuditTrail save(AuditTrail auditTrail) {
        return auditTrailRepository.save(auditTrail);
    }

    @Override
    public AuditTrail getAuditTrailByDroneId(Long id) {
        return auditTrailRepository.findByDroneId(id);
    }

    @Override
    public List<AuditTrail> getAll() {
        return auditTrailRepository.findAll();
    }
}
