package com.musala.task.entities;

import com.musala.task.enums.ModelEnum;
import com.musala.task.enums.StateEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=7, max=100, message= "Serial Number must be max of 100 characters")
    @Pattern(regexp="^[a-zA-Z]*$", message="Invalid serialNumber pattern!")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private ModelEnum model;
    @Max(value = 500, message = "Weight must be max of 500g")
    private int weightLimit;
    @Max(value = 100, message = "Battery Capacity must be max of 100%")
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    private StateEnum state;
}
