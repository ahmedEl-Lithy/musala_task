package com.musala.task.commands;

import com.musala.task.enums.StateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MedicationCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp="^[a-zA-Z0-9_-]*$", message="Invalid name pattern!")
    private String name;
    private String weight;

    @Pattern(regexp="^[A-Z0-9_]*$", message="Invalid code pattern!")
    private String code;
//    private MultipartFile image;
}
