package com.musala.task.mappers;

import com.musala.task.dtos.MedicationRequestDto;
import com.musala.task.dtos.MedicationResponseDto;
import com.musala.task.models.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MedicationObjMapper {
    MedicationObjMapper INSTANCE = Mappers.getMapper(MedicationObjMapper.class);

    Medication medicationRequestDtoToMedication(MedicationRequestDto medicationRequestDto);

    MedicationResponseDto MedicationToMedicationResponseDto(Medication medication);
}