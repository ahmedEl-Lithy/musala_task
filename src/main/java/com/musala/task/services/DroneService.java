package com.musala.task.services;

import com.musala.task.dto.MedicationRequestDto;
import com.musala.task.entities.Medication;
import com.musala.task.entities.Drone;
import com.musala.task.enums.StateEnum;
import com.musala.task.exceptions.MaxTenDronesException;
import com.musala.task.repositories.DroneRepository;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DroneService {

    final DroneRepository droneRepository;
    final MedicationRepository medicationRepository;

    @Value("${image.path}")
    String imagePath;
    final int maxFleetSize = 10;

    //Below map used as caching layer
    //TODO: populate with app startup
//    Map<Long, Drone> dronesMap = new HashMap<>();

    public List<Drone> getAllDrones() {
//        TODO: update the caching map
        List<Drone> dronesList = droneRepository.findAll();
//        dronesMap = dronesList.stream()
//                .collect(Collectors.toMap(Drone::getId, Function.identity()));
        return dronesList;
    }

    public Drone retreiveDrone(int id) {
        return droneRepository.findById((long) id).get();
    }

    public void registerDrone(Drone drone) {
//        checkDronesMap();
        List<Drone> dronesList = getAllDrones();
        if (dronesList.size() == maxFleetSize) {
            throw new MaxTenDronesException("Reached max fleet Capacity");
        } else {
            droneRepository.save(drone);
//            dronesMap.put(drone.getId(), drone);
        }
    }

//    private void checkDronesMap() {
//        if (dronesMap.isEmpty()) {
//            List<Drone> dronesList = getAllDrones();
//            dronesMap = dronesList.stream()
//                    .collect(Collectors.toMap(Drone::getId, Function.identity()));
//        }
//    }

    public void deleteDrone(int id) {
        droneRepository.deleteById((long) id);
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
    }

    public void loadDroneWithMedication(int droneId, List<Medication> medicationList, List<String> base64Images) {
        Drone drone = retreiveDrone(droneId);

        int i = 0;
        for (Medication medication : medicationList) {
            medication.setDrone(drone);
            String imageValue = base64Images.get(i);
            byte[] imageByte = Base64.decodeBase64(imageValue);

            String directory = imagePath + medication.getPhysicalImageName() + ".jpg";

            try (OutputStream stream = Files.newOutputStream(Paths.get(directory))) {
                stream.write(imageByte);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            i++;
        }
        medicationRepository.saveAll(medicationList);
    }


}