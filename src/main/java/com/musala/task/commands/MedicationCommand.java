package com.musala.task.commands;

import com.musala.task.entities.Drone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicationCommand {
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid name pattern!")
    private String name;
    private String weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid code pattern!")
    private String code;

    private String image;
}
