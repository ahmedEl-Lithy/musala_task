package com.musala.task.mappers;

import com.musala.task.dtos.RegisterDroneRequestDto;
import com.musala.task.dtos.AvaliableDroneResponseDto;
import com.musala.task.models.Drone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DroneObjMapper {

    DroneObjMapper INSTANCE = Mappers.getMapper(DroneObjMapper.class);

    AvaliableDroneResponseDto droneToAvaliableDroneResponseDto(Drone drone);

    Drone registerDroneDtoToDrone(RegisterDroneRequestDto registerDroneRequestDto);
}