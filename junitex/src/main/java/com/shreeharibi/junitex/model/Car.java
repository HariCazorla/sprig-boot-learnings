package com.shreeharibi.junitex.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotEmpty(message = "car category cannot be an empty string")
    private String category;

    @NotEmpty(message = "manufacturer cannot be an empty string")
    private String manufacturer;

    @NotEmpty(message = "model name should not be an empty string")
    private String model;

    private LocalDate year;

    private Integer capacity;
}
