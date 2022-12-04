package com.shreeharibi.junitex.repository;

import com.shreeharibi.junitex.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModel(String model);

    Optional<List<Car>> findAllByManufacturer(String manufacturer);
}
