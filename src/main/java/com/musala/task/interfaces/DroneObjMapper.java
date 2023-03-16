package com.musala.task.interfaces;

import com.musala.task.dtos.DroneRequestDto;
import com.musala.task.dtos.DroneResponseDto;
import com.musala.task.models.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DroneObjMapper {

    DroneObjMapper INSTANCE = Mappers.getMapper(DroneObjMapper.class);

    DroneResponseDto droneToDroneResponseDto(Drone drone);

    Drone droneDtoToDrone(DroneRequestDto droneRequestDto);
}