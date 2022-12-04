package com.shreeharibi.junitex.service;

import com.shreeharibi.junitex.exceptions.CarException;
import com.shreeharibi.junitex.exceptions.NoCarsFoundException;
import com.shreeharibi.junitex.model.Car;
import com.shreeharibi.junitex.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public List<Car> getAllCars() {
        List<Car> allCars = repository.findAll();
        if (allCars.isEmpty()) {
            throw new NoCarsFoundException("Cars not found");
        }
        return allCars;
    }

    public Car addCar(Car car) {
        Car savedCar = repository.save(car);
        if (savedCar == null) {
            throw new CarException("Failed to save the Car");
        }
        return savedCar;
    }
}
