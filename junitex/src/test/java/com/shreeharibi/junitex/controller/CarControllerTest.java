package com.shreeharibi.junitex.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shreeharibi.junitex.exceptions.CarException;
import com.shreeharibi.junitex.exceptions.NoCarsFoundException;
import com.shreeharibi.junitex.model.Car;
import com.shreeharibi.junitex.service.CarService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CarController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CarService service;

  private Car car;

  @BeforeAll
  public void init() {
    car = Car.builder()
            .year(LocalDate.now())
            .capacity(5)
            .category("Sedan")
            .manufacturer("BMW")
            .model("model123")
            .build();
  }

  @Test
  public void getAllCars_ShouldReuturnNoContent() throws Exception {
    //Arrange
    when(service.getAllCars()).thenThrow(new NoCarsFoundException("Cars not found"));

    //Act and assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/car/all"))
            .andExpect(status().is(204));
  }

  @Test
  public void getAllCars_ShouldReuturnOk() throws Exception {
    //Arrange
    when(service.getAllCars()).thenReturn(Collections.singletonList(car));

    //Act and assert
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/car/all"))
            .andExpect(status().is(200));
  }

  @Test
  public void addCar_shouldReturnCreated() throws Exception {
    //arrange
    when(service.addCar(car)).thenReturn(car);

    //act and assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/car")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(car)))
            .andExpect(status().is(201));

  }

  @Test
  public void addCar_shouldReturnInternalServerError() throws Exception {
    //arrange
    when(service.addCar(car)).thenThrow(new CarException("Failed to save car"));

    //act and assert
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/car")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(car)))
            .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));

  }
}
