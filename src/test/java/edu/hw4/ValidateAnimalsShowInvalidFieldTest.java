package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class ValidateAnimalsShowInvalidFieldTest {

    @Test
    @DisplayName("validate animals show invalid field")
    public void validateAnimalsShowInvalidField() {
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
        Map<String, Set<String>> validationErrorMap = new HashMap<>(
                Map.of(
                        animalInvalidName.name(), Set.of("Name"),
                        animalInvalidAge.name(), Set.of("Age"),
                        animalInvalidHeightAndWeight.name(), Set.of("Height", "Weight"),
                        validAnimal.name(), Set.of(),
                        animalInvalidBites.name(), Set.of("Bites")
                ));
        Assertions.assertEquals(AnimalUtils.validateAnimalsShowInvalidField(animals), validationErrorMap);
    }
}
