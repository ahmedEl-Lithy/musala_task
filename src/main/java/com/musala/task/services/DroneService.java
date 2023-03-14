package com.musala.task.services;

import com.musala.task.entities.Drone;
import com.musala.task.enums.StateEnum;
import com.musala.task.exceptions.MaxTenDronesException;
import com.musala.task.repositories.DroneRepository;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DroneService {

    final DroneRepository droneRepository;
    final MedicationRepository medicationRepository;
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

//    public void deleteDrone(int id) {
//        droneRepository.deleteById((long) id);
//    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
    }

}