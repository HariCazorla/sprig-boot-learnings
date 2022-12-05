package com.shreeharibi.basicauth.service;

import com.shreeharibi.basicauth.model.Animal;
import com.shreeharibi.basicauth.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public Animal saveAnimal(Animal animal) {
        Optional<Animal> byName = animalRepository.findByName(animal.getName());
        if(byName.isPresent()) {
            throw new RuntimeException(String.format("Animal with %s already present in database", animal.getName()));
        }
        return animalRepository.save(animal);
    }

    public List<Animal> findAllAnimals() {
        List<Animal> all = animalRepository.findAll();
        if (all.isEmpty()) {
            throw new RuntimeException(String.format("No animals found"));
        }
        return all;
    }

}
