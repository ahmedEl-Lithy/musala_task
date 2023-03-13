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
    @NotNull(message = "[serialNumber] cannot be empty")
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private ModelEnum model;
    @Max(value = 500, message = "[Weight] must be max of 500g")
    private int weightLimit;
    @Max(value = 100, message = "[Battery Capacity] must be max of 100%")
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Medication> medicationsList;
}
