package com.musala.task.controllers;

import com.musala.task.commands.MedicationCommand;
import com.musala.task.entities.Drone;
import com.musala.task.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DroneController {
    @Autowired
    DroneService droneService;
    @GetMapping("/drones")
    private List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @PostMapping("/drones")
    private ResponseEntity<?> createDrone(@Valid @RequestBody Drone drone) {
        droneService.registerDrone(drone);
        return new ResponseEntity("New Drone created with id: " + drone.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/drones/{id}/battery")
    private ResponseEntity<?> getDroneBatteryLevel(@PathVariable("id") int id) {
        Drone drone = null;
        drone = droneService.getDroneById(id);
        return new ResponseEntity("Battery level for the Drone is: " + drone.getBatteryCapacity(), HttpStatus.OK);
    }

    @GetMapping("/drones/available")
    private List<Drone> getAvailableDronesForLoading() {
        return droneService.getAvailableDronesForLoading();
    }

    @PostMapping(value = "drones/{id}/load", consumes = {"multipart/form-data"})
    private ResponseEntity<?> loadDroneWithMedication(@RequestParam("images") MultipartFile file
//            ,  @RequestParam ("medicine") MedicationCommand medicationCommandList) {
            ,  @RequestParam ("medicine") String medicationCommandList) {
        System.out.println("medications " + medicationCommandList);
        System.out.println("file " + file);
        return null;
    }

    }