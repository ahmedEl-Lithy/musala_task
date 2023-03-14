package com.musala.task.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicationRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid name pattern!")
    private String name;
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid code pattern!")
    private String code;

    private String image;
    private String imageName;
}
