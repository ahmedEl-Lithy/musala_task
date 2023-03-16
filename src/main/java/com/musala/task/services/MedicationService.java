package com.musala.task.services;

import com.musala.task.entities.Drone;
import com.musala.task.entities.Medication;
import com.musala.task.exceptions.ReachedDroneCapcaityException;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {

    final MedicationRepository medicationRepository;

    //TODO: change to droneRepo
    final DroneService droneService;

    public List<Medication> retrieveDroneMedications(Long droneId) {
        return medicationRepository.findByDroneId(droneId);
    }

    public void loadMedication(int droneId, List<Medication> medicationList) {
        Drone drone = droneService.retreiveDrone(droneId);

        int medicationsWeight = medicationList.stream()
                .map(Medication::getWeight).mapToInt(Integer::intValue).sum();

        int sumWeights = drone.getWeightSum() + medicationsWeight;
        if (sumWeights < drone.getWeightLimit()) {
            medicationList.forEach(medication -> medication.setDrone(drone));
            drone.setWeightSum(sumWeights);
            medicationRepository.saveAll(medicationList);
        } else {
            throw new ReachedDroneCapcaityException("Reached Drone Weight Capacity");

        }
    }
}