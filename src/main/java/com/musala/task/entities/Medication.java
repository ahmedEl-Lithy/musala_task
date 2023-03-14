package com.musala.task.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid name pattern!")
    private String name;
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid code pattern!")
    private String code;

    private String imageName;

    private String physicalImageName;

    @ManyToOne
    private Drone drone;
}
