package com.musala.task.dtos;

import com.musala.task.enums.ModelEnum;
import com.musala.task.enums.StateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DroneResponseDto {
    private Long id;
    private String serialNumber;

    private ModelEnum model;

    private int weightLimit;

    private int batteryCapacity;

    private StateEnum state;
}
