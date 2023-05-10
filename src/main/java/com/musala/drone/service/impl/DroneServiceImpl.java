package com.musala.drone.service.impl;

import com.musala.drone.entity.Drone;
import com.musala.drone.enums.State;
import com.musala.drone.repository.DroneRepository;
import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Override
    public Drone save(Drone drone) {
        return droneRepository.save(drone);

    }

    @Override
    public Optional<Drone> findDroneById(Long id) {
        return droneRepository.findById(id);
    }

    @Override
    public List<Drone> findDroneBySate(State state) {
        return droneRepository.findByState(state);
    }

    @Override
    public List<Drone> findAll() {
        return droneRepository.findAll();
    }
}
