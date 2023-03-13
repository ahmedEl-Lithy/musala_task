package com.musala.task.services;

import com.musala.task.entities.Drone;
import com.musala.task.enums.StateEnum;
import com.musala.task.repositories.DroneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneService {

    final DroneRepository droneRepository;

    public List<Drone> getAllDrones() {
        return new ArrayList<>(droneRepository.findAll());
    }

    public Drone getDroneById(int id) {
        return droneRepository.findById((long) id).get();
    }

    public void registerDrone(Drone drone) {
        droneRepository.save(drone);
    }

    public void deleteDrone(int id) {
        droneRepository.deleteById((long) id);
    }

    public List<Drone> getAvailableDronesForLoading() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
    }

}