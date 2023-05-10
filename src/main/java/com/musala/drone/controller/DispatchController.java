package com.musala.drone.controller;

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
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/drone")
public class DispatchController {
    @Autowired
    DroneService droneService;

    @Autowired
    AuditTrailService auditTrailService;

    @Autowired
    DispatchService dispatchService;

    @Autowired
    MedicationService medicationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDrone(@Valid @RequestBody Drone drone){
        return new ResponseEntity<>(droneService.save(drone), HttpStatus.CREATED);

    }
    @PostMapping("/{id}/load")
    public ResponseEntity<?> loadDrone(@PathVariable(name = "id") Long id,@RequestBody List<Medication> medications){
        try {
            dispatchService.load(id,medications);
            return new ResponseEntity<>(medications,HttpStatus.CREATED);

        } catch (DroneNotFoundException e) {

            return ResponseEntity.status(HttpStatus.LOCKED).body(String.format(" drone not found "));

        } catch (DroneWeightExceededException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("load above drone weight limit"));

        } catch (DroneBatteryException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("drone below minimum battery capacity"));
        }


    }

    @GetMapping("/droneavailable")
    public ResponseEntity<?> getAvailableDrone(){
        return new ResponseEntity<>(droneService.findDroneBySate(State.IDLE),HttpStatus.OK);
    }

    @GetMapping("/{id}/loadedmedication")
    public ResponseEntity<?> getDroneMedication(@PathVariable(name ="id" ) Long id){
        AuditTrail auditTrail=auditTrailService.getAuditTrailByDroneId(id);
        if(auditTrail==null){
            return new ResponseEntity<>("no audit trail for this drone",HttpStatus.EXPECTATION_FAILED);
        }
        if(!(auditTrail.getDrone().getState().equals(State.LOADED)||auditTrail.getDrone().getState().equals(State.DELIVERING))){
            return new ResponseEntity<>("drone is empty",HttpStatus.OK);
        }
       return new ResponseEntity<>(auditTrail.getMedications(),HttpStatus.OK);
    }
    @GetMapping("/audit")
    public ResponseEntity<?> getAuditTrails(){
        return new ResponseEntity<>(auditTrailService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/medication")
    public ResponseEntity<?> getAllMedication(){
        return new ResponseEntity<>(medicationService.getAll(),HttpStatus.OK);
    }



}
