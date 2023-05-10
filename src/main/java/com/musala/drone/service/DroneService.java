package com.musala.drone.service;


import com.musala.drone.entity.Drone;
import com.musala.drone.enums.State;

import java.util.List;
import java.util.Optional;

public interface DroneService {
    public Drone save(Drone drone);
    public Optional<Drone> findDroneById(Long id);
    public List<Drone> findDroneBySate(State state);

    public List<Drone> findAll();
}
