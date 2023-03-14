package com.musala.task.entities;

import com.musala.task.enums.ModelEnum;
import com.musala.task.enums.StateEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 100, message = "[serialNumber] must be max of 100 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid [serialNumber] pattern!")
    @NotBlank(message = "[serialNumber] cannot be empty")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "[model] cannot be empty")
    private ModelEnum model;
    @Max(value = 500, message = "[Weight] must be max of 500g")
    @NotBlank(message = "[Weight] cannot be empty")
    private int weightLimit;
    @Max(value = 100, message = "[Battery Capacity] must be max of 100%")
    @NotBlank(message = "[Battery Capacity] cannot be empty")
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "[state] cannot be empty")
    private StateEnum state;

    @OneToMany(mappedBy = "drone")
    private List<Medication> medicationsList;
}
