package com.shreeharibi.junitex.repository;

import com.shreeharibi.junitex.model.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarRepositoryTest {
    @Autowired
    private CarRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private Car car1, car2;

    @BeforeEach
    public void init() {
        car1 = Car.builder()
                .year(LocalDate.now())
                .capacity(5)
                .category("Sedan")
                .manufacturer("BMW")
                .model("model123")
                .build();

        car2 = Car.builder()
                .year(LocalDate.now())
                .capacity(5)
                .category("Sedan")
                .manufacturer("Audi")
                .model("model123")
                .build();
    }

    @Test
    public void getAllCars_ReturnsCars() {
        entityManager.persistAndFlush(car1);
        entityManager.persistAndFlush(car2);

        List<Car> allCars = repository.findAll();

        assertThat(allCars.size()).isEqualTo(2);
    }

    @Test
    public void addCar_ShoulReturnCar() {
        Car save = repository.save(car1);
        assertThat(save).isNotNull();
        assertThat(save.getId()).isNotNull();
    }

    @Test
    public void findByModel_ShouldReturnCar() {
        entityManager.persistAndFlush(car1);

        Optional<Car> model123 = repository.findByModel("model123");
        assertThat(model123.isPresent()).isTrue();
    }

    @Test
    public void findAllByManufacturer_ShouldReturnListOfCars() {
        entityManager.persistAndFlush(car1);
        Optional<List<Car>> bmw = repository.findAllByManufacturer("BMW");
        assertThat(bmw).isNotNull();
        assertThat(bmw.get().size()).isEqualTo(1);
    }
}
