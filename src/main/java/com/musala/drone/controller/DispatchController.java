package com.musala.drone.controller;

import com.musala.drone.entity.Drone;

import com.musala.drone.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/drone")
public class DispatchController {
    @Autowired
    DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<?> registerDrone(@Valid @RequestBody Drone drone){
        return new ResponseEntity<>(droneService.save(drone), HttpStatus.CREATED);

    }


}
