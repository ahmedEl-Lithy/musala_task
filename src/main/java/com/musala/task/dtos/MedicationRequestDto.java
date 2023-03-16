package com.musala.task.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicationRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid [name] pattern!")
    private String name;
    @Min(value = 1, message = "[Weight] must be min of 1g")
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid [code] pattern!")
    @NotBlank(message = "[code] cannot be empty")
    private String code;

    private String image;
    @NotBlank(message = "[imageName] cannot be empty")
    private String imageName;
}
