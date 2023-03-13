package com.musala.task.entities;

import com.musala.task.entities.Drone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "Invalid name pattern!")
    private String name;
    private String weight;

    @Pattern(regexp = "^[A-Z0-9_]*$", message = "Invalid code pattern!")
    private String code;

    @Lob
    private byte[] image;

    @ManyToOne
//    @JoinColumn(name="drone_id", nullable=false)
    private Drone drone;
}
