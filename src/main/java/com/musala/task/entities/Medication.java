package com.musala.task.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid name pattern!")
    private String name;
    @NotNull(message = "[weight] cannot be empty")
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid code pattern!")
    @NotBlank(message = "[code] cannot be empty")
    private String code;

    @NotBlank(message = "[imageName] cannot be empty")
    private String imageName;

    private String physicalImageName;

    @ManyToOne
    private Drone drone;
}
