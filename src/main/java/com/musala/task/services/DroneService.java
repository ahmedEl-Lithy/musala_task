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
//    final MedicationRepository medicationRepository;
    final int maxFleetSize = 10;

    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    public Drone retreiveDrone(int id) {
        return droneRepository.findById((long) id).get();
    }

    public void registerDrone(Drone drone) {
        List<Drone> dronesList = getAllDrones();
        if (dronesList.size() == maxFleetSize) {
            throw new MaxTenDronesException("Reached max fleet Capacity");
        } else {
            droneRepository.save(drone);
        }
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
    }

}