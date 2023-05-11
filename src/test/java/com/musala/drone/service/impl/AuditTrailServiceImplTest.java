package com.musala.drone.service.impl;

import com.musala.drone.entity.AuditTrail;
import com.musala.drone.entity.Drone;
import com.musala.drone.repository.AuditTrailRepository;
import com.musala.drone.repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuditTrailServiceImplTest {
    @Mock
    AuditTrailRepository auditTrailRepository;

    @InjectMocks
    AuditTrailServiceImpl auditTrailService;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
    }

    @Test
    void getAuditTrailByDroneId() {
        AuditTrail auditTrail=new AuditTrail();
        auditTrail.setId(Long.valueOf(1));
        Mockito.lenient().when(auditTrailRepository.findByDroneId(Long.valueOf(1))).thenReturn(auditTrail);
        AuditTrail auditTrail1=auditTrailService.getAuditTrailByDroneId(Long.valueOf(1));
        Mockito.verify(auditTrailRepository,Mockito.times(1)).findByDroneId(Long.valueOf(1));
        assertEquals(Long.valueOf(1),auditTrail1.getId());
    }

    @Test
    void getAll() {
    }
}