package com.musala.drone.service.impl;

import com.musala.drone.entity.Drone;
import com.musala.drone.exception.DroneNotFoundException;
import com.musala.drone.service.AuditTrailService;
import com.musala.drone.service.DroneService;
import com.musala.drone.service.MedicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DispatchServiceImplTest {

    @Mock
    DroneService droneService;
    @Mock
    MedicationService medicationService;
    @Mock
    AuditTrailService auditTrailService;

    @InjectMocks
    DispatchServiceImpl dispatchService;

    @Test
    void load() {

        Optional<Drone> optionalDrone= Optional.empty();
        Mockito.lenient().when(droneService.findDroneById(Long.valueOf(1))).thenReturn(optionalDrone);
        Optional<Drone> optionalDrone1=droneService.findDroneById(Long.valueOf(1));
        Mockito.lenient().when(optionalDrone1.isPresent()).thenThrow(new RuntimeException());
        optionalDrone1.isPresent();
        assertThrows(RuntimeException.class,()->dispatchService.load(Long.valueOf(1),new ArrayList<>()));



    }
}