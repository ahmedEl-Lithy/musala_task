package com.musala.task.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicationResponseDto {
    private String name;
    private int weight;

    private String code;

    private String image;
    private String imageName;
}
