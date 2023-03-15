package com.musala.task.controllers;

import com.musala.task.dto.DroneRequestDto;
import com.musala.task.dto.MedicationRequestDto;
import com.musala.task.entities.Drone;
import com.musala.task.entities.Medication;
import com.musala.task.services.DroneService;
import com.musala.task.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.musala.task.utils.Utils.encodeFileToBase64Binary;
import static com.musala.task.utils.Utils.saveMedicationImageToPath;

@RequiredArgsConstructor
@RestController
public class MainController {
    final DroneService droneService;
    final MedicationService medicationService;

    @Value("${image.path}")
    String imagePath;

    @GetMapping("/drones")
    private List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    @Validated
    @PostMapping("/drones")
    private ResponseEntity<?> createDrone(@Valid @RequestBody DroneRequestDto droneRequestDto) {
        Drone drone = new Drone();
        drone.setSerialNumber(droneRequestDto.getSerialNumber());
        drone.setState(droneRequestDto.getState());
        drone.setModel(droneRequestDto.getModel());
        drone.setWeightLimit(droneRequestDto.getWeightLimit());
        drone.setBatteryCapacity(droneRequestDto.getBatteryCapacity());

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

    @Validated
    @PostMapping("drones/{id}/load")
    private ResponseEntity<?> loadDroneWithMedication(@PathVariable("id") int droneId,
                                                      @Valid @RequestBody List<MedicationRequestDto> medicationRequestDtoList) throws IOException {
        List<Medication> medicationList = new ArrayList<>();
        for (MedicationRequestDto medicationRequestDto : medicationRequestDtoList) {
            Medication medication = new Medication();
            medication.setCode(medicationRequestDto.getCode());
            medication.setName(medicationRequestDto.getName());
            medication.setWeight(medicationRequestDto.getWeight());
            medication.setImageName(medicationRequestDto.getImageName());
            medication.setPhysicalImageName(UUID.randomUUID().toString());
            medicationList.add(medication);

            saveMedicationImageToPath(medicationRequestDto.getImage(), medication.getPhysicalImageName());
        }
        medicationService.loadMedication(droneId, medicationList);
        return new ResponseEntity<>("Drone loaded successfully with medication", HttpStatus.CREATED);
    }

    @GetMapping("/drones/{id}/medication")
    private List<MedicationRequestDto> getDroneMedications(@PathVariable("id") Long droneId) {

        List<Medication> medicationList = medicationService.retrieveDroneMedications(droneId);
        List<MedicationRequestDto> medicationRequestDtoList = new ArrayList<>();
        for (Medication medication : medicationList) {
            MedicationRequestDto medicationRequestDto = new MedicationRequestDto();
            medicationRequestDto.setName(medication.getName());
            medicationRequestDto.setWeight(medication.getWeight());
            medicationRequestDto.setCode(medication.getCode());
            medicationRequestDto.setImageName(medication.getImageName());

            File file = new File(imagePath + medication.getPhysicalImageName() + ".jpg");
            String encodstring = encodeFileToBase64Binary(file);

            medicationRequestDto.setImage(encodstring);

            medicationRequestDtoList.add(medicationRequestDto);
        }

        return medicationRequestDtoList;
    }
}
