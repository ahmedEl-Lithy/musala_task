package com.musala.task.services;

import com.musala.task.commands.MedicationCommand;
import com.musala.task.entities.Medication;
import com.musala.task.entities.Drone;
import com.musala.task.enums.StateEnum;
import com.musala.task.exceptions.MaxTenDronesException;
import com.musala.task.repositories.DroneRepository;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DroneService {

    final DroneRepository droneRepository;
    final MedicationRepository medicationRepository;
    final int maxFleetSize = 10;

    //Below map used as caching layer
    Map<Long, Drone> dronesMap = new HashMap<>();

    public List<Drone> getAllDrones() {
        //TODO: update the caching map??
        List<Drone> dronesList = droneRepository.findAll();
        dronesMap = dronesList.stream()
                .collect(Collectors.toMap(Drone::getId, Function.identity()));
        return dronesList;
    }

    public Drone retreiveDrone(int id) {
        return droneRepository.findById((long) id).get();
    }

    public void registerDrone(Drone drone) {
        checkDronesMap();
        if (dronesMap.size() == maxFleetSize) {
            throw new MaxTenDronesException("Reached max fleet Capacity");
        } else {
            droneRepository.save(drone);
            dronesMap.put(drone.getId(), drone);
        }
    }

    private void checkDronesMap() {
        if (dronesMap.isEmpty()) {
            List<Drone> dronesList = getAllDrones();
            dronesMap = dronesList.stream()
                    .collect(Collectors.toMap(Drone::getId, Function.identity()));
        }
    }

    public void deleteDrone(int id) {
        droneRepository.deleteById((long) id);
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
    }

    public void loadDroneWithMedication(int droneId, List<MedicationCommand> medicationCommandList) {
        Drone drone = retreiveDrone(droneId);
        List<Medication> medicationList = new ArrayList<>();
        for (MedicationCommand medicationCommand : medicationCommandList) {
            Medication medication = new Medication();
            medication.setCode(medicationCommand.getCode());
            medication.setName(medicationCommand.getName());
            medication.setWeight(medicationCommand.getWeight());
            byte[] imageByte = Base64.decodeBase64(medicationCommand.getImage());
            medication.setImage(imageByte);
            medicationList.add(medication);
        }
        drone.setMedicationsList(medicationList);
        droneRepository.save(drone);
        for (Medication medication : medicationList) {
            medication.setDrone(drone);
        }
        medicationRepository.saveAll(medicationList);
    }


}