package com.musala.drone.repository;

import com.musala.drone.entity.Drone;
import com.musala.drone.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone,Long> {
    public List<Drone> findByState(State state);
}
