package com.shreeharibi.beanvalidationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Positive(message = "Age should be a positive number")
    @NotNull(message = "Age cannot be null")
    @Max(message = "Maximum age limit is 100", value = 100)
    private Integer age;

    @NotNull(message = "Address cannot be null")
    @Valid
    private Address address;
}
