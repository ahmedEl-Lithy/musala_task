package com.musala.task.scheduler;

import com.musala.task.models.Drone;
import com.musala.task.services.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class Scheduler {

    final DroneService droneService;

    private static int generateRandomNumber() {
        Random randI = new Random();

        return randI.nextInt(100);
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleBatteryChecking() {
        List<Drone> dronesList = droneService.getAllDrones();

        dronesList.forEach(drone -> log.info("Drone with [SerialNumber] " + drone.getSerialNumber() + " has battery of " + generateRandomNumber() + "%"));
    }
}
