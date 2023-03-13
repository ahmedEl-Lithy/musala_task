package com.musala.task.controllers;

import com.musala.task.commands.MedicationCommand;
import com.musala.task.entities.Drone;
import com.musala.task.entities.Medication;
import com.musala.task.services.DroneService;
import com.musala.task.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
//@Validated
public class DroneController {
    final DroneService droneService;
    final MedicationService medicationService;

    @GetMapping("/drones")
    private List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @PostMapping("/drones")
    private ResponseEntity<?> createDrone(@Valid @RequestBody Drone drone) {
        droneService.registerDrone(drone);
        return new ResponseEntity<>("New Drone created with id: " + drone.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/drones/{id}/battery")
    private ResponseEntity<?> getDroneBatteryLevel(@PathVariable("id") int id) {
        Drone drone = droneService.retreiveDrone(id);
        return new ResponseEntity<>("Battery level for the Drone is: " + drone.getBatteryCapacity(), HttpStatus.OK);
    }

    @GetMapping("/drones/available")
    private List<Drone> getAvailableDronesForLoading() {
        return droneService.getAvailableDronesForLoading();
    }

    @PostMapping("drones/{id}/load")
    private ResponseEntity<?> loadDroneWithMedication(@PathVariable("id") int droneId,
                                                      @Valid @RequestBody List<MedicationCommand> medicationList) {

        droneService.loadDroneWithMedication(droneId, medicationList);
        return new ResponseEntity<>("Drone loaded successfully with medication", HttpStatus.CREATED);
    }

    @GetMapping("drones/{id}/medication")
    private List<Medication> getDroneMedications(@PathVariable("id") Long droneId) {

        List<Medication> medicationList = medicationService.retrieveDroneMedications(droneId);
        return medicationList;
    }

}
