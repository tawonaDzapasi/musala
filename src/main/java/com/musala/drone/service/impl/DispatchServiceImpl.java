package com.musala.drone.service.impl;

import com.musala.drone.entity.AuditTrail;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;
import com.musala.drone.enums.State;
import com.musala.drone.exception.DroneBatteryException;
import com.musala.drone.exception.DroneNotFoundException;
import com.musala.drone.exception.DroneWeightExceededException;
import com.musala.drone.service.AuditTrailService;
import com.musala.drone.service.DispatchService;
import com.musala.drone.service.DroneService;
import com.musala.drone.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DispatchServiceImpl implements DispatchService {
    @Autowired
    private DroneService droneService;
    @Autowired
    private MedicationService medicationService;
    @Autowired
    private AuditTrailService auditTrailService;

    @Override
    public List<Medication> load(Long droneId,List<Medication> medications) throws DroneBatteryException,DroneNotFoundException,DroneWeightExceededException{

        Optional<Drone> optionalDrone=droneService.findDroneById(droneId);
        if(!optionalDrone.isPresent()){
            throw new DroneNotFoundException();
        }
        Drone drone=optionalDrone.get();
        if( !(drone.getState().equals(State.IDLE) || drone.getState().equals(State.LOADING))){
            throw new DroneNotFoundException();
        }
        if(drone.getBatteryCapacity()<25){
            throw new DroneBatteryException();
        }
        drone.setState(State.LOADING);
        droneService.save(drone);
        Integer totalWeight=medications.stream().map(m->m.getWeight()).reduce(0,Integer::sum);
        if(totalWeight>drone.getWeightLimit()) {
            throw new DroneWeightExceededException();
        }
        drone.setState(State.LOADED);
        AuditTrail auditTrail=new AuditTrail();
        auditTrail.setDrone(drone);
        auditTrail.setLocationName("somewhere");
        auditTrail.setMedications(medications);
        auditTrailService.save(auditTrail);




        return medications;

    }
}
