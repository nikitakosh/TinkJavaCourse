package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetKOldestAnimalsTest {

    @Test
    @DisplayName("get k oldest animals")
    public void getKOldestAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 7, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 3, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 2, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 10, 70, 10, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getKOldestAnimal(animals, 3), new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE));
    }
}
