package com.musala.task.services;

import com.musala.task.entities.Medication;
import com.musala.task.repositories.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {

    final MedicationRepository medicationRepository;

    public List<Medication> retrieveDroneMedications(Long droneId) {
        return medicationRepository.findByDroneId(droneId);
    }
}