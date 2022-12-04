package com.shreeharibi.junitex.config;

import com.shreeharibi.junitex.model.Car;
import com.shreeharibi.junitex.repository.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class Init {
    @Bean
    CommandLineRunner commandLineRunner(CarRepository repository) {
        return args -> {
            Car car1 = Car.builder()
                    .year(LocalDate.now())
                    .capacity(5)
                    .category("Sedan")
                    .manufacturer("BMW")
                    .model("seriers A")
                    .build();

            Car car2 = Car.builder()
                    .year(LocalDate.now())
                    .capacity(5)
                    .category("Sedan")
                    .manufacturer("BMW")
                    .model("seriers B")
                    .build();

            Car car3 = Car.builder()
                    .year(LocalDate.now())
                    .capacity(5)
                    .category("Sedan")
                    .manufacturer("Audi")
                    .model("seriers B")
                    .build();

            repository.saveAll(List.of(car1, car2, car3));
        };
    }
}
