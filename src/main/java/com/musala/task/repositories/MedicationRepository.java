package com.musala.task.repositories;

import com.musala.task.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    //TODO: check to review

    List<Medication> findByDroneId(Long droneId);
}