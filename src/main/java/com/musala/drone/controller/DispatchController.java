package com.musala.drone.controller;

import com.musala.drone.entity.AuditTrail;
import com.musala.drone.entity.Drone;
import com.musala.drone.entity.Medication;
import com.musala.drone.exception.DroneBatteryException;
import com.musala.drone.exception.DroneNotFoundException;
import com.musala.drone.exception.DroneWeightExceededException;
import com.musala.drone.service.DispatchService;
import com.musala.drone.service.DroneService;
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
    DispatchService dispatchService;


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




}
