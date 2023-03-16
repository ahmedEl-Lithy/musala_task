package com.musala.task.services;

import com.musala.task.models.Drone;
import com.musala.task.models.Medication;
import com.musala.task.customexception.ReachedDroneCapcaityException;
import com.musala.task.repositories.DroneRepository;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {

    final MedicationRepository medicationRepository;
    final DroneRepository droneRepository;

    public List<Medication> retrieveDroneMedications(Long droneId) {
        return medicationRepository.findByDroneId(droneId);
    }

    public void loadMedication(int droneId, List<Medication> medicationList) {
        Drone drone = droneRepository.getById((long) droneId);

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