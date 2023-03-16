package com.musala.task.repositories;

import com.musala.task.models.Drone;
import com.musala.task.enums.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository <Drone, Long> {
    List<Drone> findByStateAndBatteryCapacityGreaterThanEqual(StateEnum state, int batteryCapacity);

}