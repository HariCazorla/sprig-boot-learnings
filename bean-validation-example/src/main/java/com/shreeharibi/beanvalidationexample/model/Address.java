package com.shreeharibi.beanvalidationexample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {

    @NotEmpty(message = "Street cannot be empty")
    private String street;

    @NotEmpty(message = "House number cannot be empty")
    private String houseNumber;

    @NotEmpty(message = "City name cannot be empty")
    private String city;

    @NotEmpty(message = "Pin code cannot be empty")
    private String pincode;

    @NotEmpty(message = "Country name cannot be empty")
    private String country;
}
