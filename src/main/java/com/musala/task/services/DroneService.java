package com.musala.task.services;

import com.musala.task.entities.Drone;
import com.musala.task.enums.StateEnum;
import com.musala.task.repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DroneService {

    @Autowired
    DroneRepository droneRepository;

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
        List<Drone> dronesList = new ArrayList<>();
        dronesList = droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(StateEnum.IDLE, 25);
        return dronesList;
    }

}