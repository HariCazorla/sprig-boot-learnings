package com.shreeharibi.basicauth.repository;

import com.shreeharibi.basicauth.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);

    Optional<List<Animal>> findAllByName(String name);

    Optional<List<Animal>> findAllByType(String type);

    Optional<List<Animal>> findAllByClassification(String classification);
}
