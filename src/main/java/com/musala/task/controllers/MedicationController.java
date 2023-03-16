package com.musala.task.controllers;

import com.musala.task.dtos.MedicationRequestDto;
import com.musala.task.dtos.MedicationResponseDto;
import com.musala.task.models.Medication;
import com.musala.task.interfaces.MedicationObjMapper;
import com.musala.task.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.musala.task.utils.ImageUtils.encodeFileToBase64Binary;
import static com.musala.task.utils.ImageUtils.saveMedicationImageToPath;

@RequiredArgsConstructor
@RestController
public class MedicationController {
    final MedicationService medicationService;

    @Value("${image.path}")
    String imagePath;

    @Validated
    @PostMapping("drones/{id}/load")
    private ResponseEntity<?> loadDroneWithMedication(@PathVariable("id") int droneId,
                                                      @Valid @RequestBody List<MedicationRequestDto> medicationRequestDtoList) throws IOException {
        List<Medication> medicationList = new ArrayList<>();
        for (MedicationRequestDto medicationRequestDto : medicationRequestDtoList) {
            Medication medication = MedicationObjMapper.INSTANCE.medicationRequestDtoToMedication(medicationRequestDto);
            medication.setPhysicalImageName(UUID.randomUUID().toString());
            medicationList.add(medication);

            saveMedicationImageToPath(medicationRequestDto.getImage(), medication.getPhysicalImageName(), imagePath);
        }
        medicationService.loadMedication(droneId, medicationList);
        return new ResponseEntity<>("Drone loaded successfully with medication", HttpStatus.CREATED);
    }

    @GetMapping("/drones/{id}/medication")
    private List<MedicationResponseDto> getDroneMedications(@PathVariable("id") Long droneId) throws IOException {

        List<Medication> medicationList = medicationService.retrieveDroneMedications(droneId);
        List<MedicationResponseDto> medicationResponseDtoList = new ArrayList<>();
        for (Medication medication : medicationList) {
            MedicationResponseDto medicationResponseDto = MedicationObjMapper.INSTANCE.MedicationToMedicationResponseDto(medication);

            File file = new File(imagePath + medication.getPhysicalImageName() + ".jpg");
            String encodstring = encodeFileToBase64Binary(file);
            medicationResponseDto.setImage(encodstring);

            medicationResponseDtoList.add(medicationResponseDto);
        }

        return medicationResponseDtoList;
    }
}
