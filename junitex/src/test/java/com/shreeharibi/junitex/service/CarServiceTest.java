package com.shreeharibi.junitex.service;

import com.shreeharibi.junitex.exceptions.CarException;
import com.shreeharibi.junitex.exceptions.NoCarsFoundException;
import com.shreeharibi.junitex.model.Car;
import com.shreeharibi.junitex.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void getAllCars_ShouldReturnNonEmptyList() {
        //arrange
        when(carRepository.findAll()).thenReturn(Collections.singletonList(new Car()));
        //act
        List<Car> allCars = carService.getAllCars();
        //assert
        assertThat(allCars).isNotEmpty();
        assertThat(allCars.size()).isEqualTo(1);
    }

    @Test
    public void getAllCars_ShouldThrowNoCarFoundException() {
        //arrange
        when(carRepository.findAll()).thenReturn(Collections.emptyList());
        //act and assert
        assertThatThrownBy(() -> carService.getAllCars()).isInstanceOf(NoCarsFoundException.class);
    }

    @Test
    public void addCar_ShouldReturnCar() {
        //arrange
        Car car = Car.builder()
                .year(LocalDate.now())
                .capacity(5)
                .category("Sedan")
                .manufacturer("BMW")
                .model("seriers A")
                .build();
        when(carRepository.save(car)).thenReturn(car);

        //act
        Car car1 = carService.addCar(car);
        assertThat(car1).isNotNull();
        assertThat(car1).isInstanceOf(Car.class);
        assertThat(car1.getManufacturer()).isEqualTo(car.getManufacturer());
    }

    @Test
    public void addCar_ShouldThrowException() {
        //arrange
        Car car = Car.builder()
                .year(LocalDate.now())
                .capacity(5)
                .category("Sedan")
                .manufacturer("BMW")
                .model("seriers A")
                .build();
        when(carRepository.save(car)).thenThrow(new CarException("Failed to save car"));

        //act and assert
        assertThatThrownBy(() -> carService.addCar(car))
                .isInstanceOf(CarException.class)
                .hasMessageContaining("Failed to save car");
    }
}
