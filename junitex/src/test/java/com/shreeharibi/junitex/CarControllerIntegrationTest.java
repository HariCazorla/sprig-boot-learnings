package com.shreeharibi.junitex;

import com.shreeharibi.junitex.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public CarControllerIntegrationTest() {
    }

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
    public void testGetAllCars_ShouldReturnOk() {
        //Arrange

        //Act
        ResponseEntity<Car[]> response = restTemplate.getForEntity("/api/v1/car/all", Car[].class);

        //Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    @Test
    public void testAddCar_ShouldReturnCreated() {
        //Arrange

        //Act
        ResponseEntity<Car> carResponseEntity = restTemplate.postForEntity("/api/v1/car", car, Car.class);

        //Assert
        assertThat(carResponseEntity).isNotNull();
        assertThat(carResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(carResponseEntity.getBody()).isInstanceOf(Car.class);
        assertThat(carResponseEntity.getBody().getManufacturer()).isEqualTo(car.getManufacturer());
    }
}
