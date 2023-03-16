package com.musala.task.controllers;

import com.musala.task.dtos.DroneRequestDto;
import com.musala.task.dtos.DroneResponseDto;
import com.musala.task.models.Drone;
import com.musala.task.interfaces.DroneObjMapper;
import com.musala.task.services.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class DroneController {
    final DroneService droneService;

    @GetMapping("/drones")
    private List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @Validated
    @PostMapping("/drones")
    private ResponseEntity<?> createDrone(@Valid @RequestBody DroneRequestDto droneRequestDto) {
        Drone drone = DroneObjMapper.INSTANCE.droneDtoToDrone(droneRequestDto);
        droneService.registerDrone(drone);
        return new ResponseEntity<>("New Drone created with id: " + drone.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/drones/{id}/battery")
    private ResponseEntity<?> getDroneBatteryLevel(@PathVariable("id") int id) {
        Drone drone = droneService.retreiveDrone(id);
        return new ResponseEntity<>("Battery level for the Drone is: " + drone.getBatteryCapacity(), HttpStatus.OK);
    }

    @GetMapping("/drones/available")
    private List<DroneResponseDto> getAvailableDronesForLoading() {

        List<Drone> dronesList = droneService.getAvailableDronesForLoading();
        List<DroneResponseDto> droneResponseDtos = new ArrayList<>();
        dronesList.forEach(drone -> {
            DroneResponseDto droneResponseDto = DroneObjMapper.INSTANCE.droneToDroneResponseDto(drone);
            droneResponseDtos.add(droneResponseDto);
        });
        return droneResponseDtos;
    }
}
