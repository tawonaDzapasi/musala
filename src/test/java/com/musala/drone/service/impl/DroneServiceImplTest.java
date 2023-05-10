package com.musala.drone.service.impl;

import com.musala.drone.entity.Drone;
import com.musala.drone.enums.State;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DroneServiceImplTest {

    @Mock
    DroneRepository droneRepository;

    @InjectMocks
    DroneServiceImpl droneService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Drone drone=new Drone();
        drone.setId(Long.valueOf(1));
        Mockito.lenient().when(droneRepository.save(drone)).thenReturn(drone);
        Drone result=droneService.save(drone);
        Mockito.verify(droneRepository,Mockito.times(1)).save(drone);
        assertEquals(Long.valueOf(1),result.getId());

    }

    @Test
    void findDroneBySate() {
        Drone drone1=new Drone();
        drone1.setState(State.IDLE);

        Drone drone2=new Drone();
        drone1.setState(State.IDLE);

        Drone drone3=new Drone();
        drone1.setState(State.IDLE);

        List<Drone> droneList=new ArrayList<>();
        droneList.add(drone1);
        droneList.add(drone2);
        droneList.add(drone3);
        Mockito.lenient().when(droneRepository.findByState(State.IDLE)).thenReturn(droneList);

        List<Drone> droneList1=droneService.findDroneBySate(State.IDLE);

        assertEquals(3,droneList1.size());
        Mockito.verify(droneRepository,Mockito.times(1)).findByState(State.IDLE);
    }
}