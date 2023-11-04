package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetAnimalBitesAndHigher100cmTest {

    @Test
    @DisplayName("get animal bites and higher 100 cm")
    public void getAnimalBitesAndHigher100cm() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 110, 10, Boolean.TRUE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 120, 10, Boolean.TRUE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.TRUE)
        ));
        List<Animal> animalsBitesAndHigher100cm = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 110, 10, Boolean.TRUE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 120, 10, Boolean.TRUE)
        ));
        Assertions.assertEquals(AnimalUtils.getAnimalBitesAndHigher100cm(animals), animalsBitesAndHigher100cm);
    }
}
