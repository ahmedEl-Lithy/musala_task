package com.musala.task.dto;

import com.musala.task.enums.ModelEnum;
import com.musala.task.enums.StateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DroneRequestDto {
    @Size(max = 100, message = "[serialNumber] must be max of 100 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid [serialNumber] pattern!")
    @NotBlank(message = "[serialNumber] cannot be empty")
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "[model] cannot be empty")
    private ModelEnum model;

    @Max(value = 500, message = "[Weight] must be max of 500g")
    @Min(value = 10, message = "[Weight] must be min of 10g")
    private int weightLimit;

    @Max(value = 100, message = "[Battery Capacity] must be max of 100%")
    @Min(value = 1, message = "[Battery Capacity] must be min of 1%")
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "[state] cannot be empty")
    private StateEnum state;
}
