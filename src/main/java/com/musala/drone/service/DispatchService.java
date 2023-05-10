package com.musala.drone.service;

import com.musala.drone.entity.Medication;
import com.musala.drone.exception.DroneBatteryException;
import com.musala.drone.exception.DroneNotFoundException;
import com.musala.drone.exception.DroneWeightExceededException;

import java.util.List;

public interface DispatchService {
    public List<Medication> load(Long droneId,List<Medication> medications) throws DroneNotFoundException, DroneWeightExceededException, DroneBatteryException;
}
