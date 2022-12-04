package com.shreeharibi.junitex.controller;

import com.shreeharibi.junitex.exceptions.NoCarsFoundException;
import com.shreeharibi.junitex.model.Car;
import com.shreeharibi.junitex.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("all")
    public ResponseEntity<?> getAllCars() {
       List<Car> allCars = carService.getAllCars();
       return ResponseEntity.ok(allCars);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car) {
        return ResponseEntity.status(201).body(carService.addCar(car));
    }
}
