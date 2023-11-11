package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ValidateAnimalsTest {

    @Test
    @DisplayName("validate animals")
    public void validateAnimals() {
        Animal animalInvalidName = new Animal("", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE);
        Animal animalInvalidAge = new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, -5, 70, 10, Boolean.FALSE);
        Animal animalInvalidHeightAndWeight = new Animal("bobbb", Animal.Type.BIRD, Animal.Sex.M, 5, -10, -10, Boolean.FALSE);
        Animal validAnimal = new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE);
        Animal animalInvalidBites = new Animal("bobb", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, null);
        List<Animal> animals = new ArrayList<>(List.of(
                animalInvalidName,
                animalInvalidAge,
                animalInvalidHeightAndWeight,
                validAnimal,
                animalInvalidBites
        ));
        Map<String, Set<ValidationError>> validationErrorMap = new HashMap<>(
                Map.of(
                        animalInvalidName.name(), new HashSet<>(Set.of(new ValidateName())),
                        animalInvalidAge.name(), new HashSet<>(Set.of(new ValidateAge())),
                        animalInvalidHeightAndWeight.name(), new HashSet<>(Set.of(new ValidateHeight(), new ValidateWeight())),
                        validAnimal.name(), new HashSet<>(Set.of()),
                        animalInvalidBites.name(), new HashSet<>(Set.of(new ValidateBites()))
        ));
        Assertions.assertEquals(AnimalUtils.validateAnimals(animals), validationErrorMap);
    }
}
