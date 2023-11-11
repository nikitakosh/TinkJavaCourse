package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IsSpiderBitesMoreOftenThanDogTest {

    @Test
    @DisplayName("is spider bites more often than dog")
    public void IsSpiderBitesMoreOftenThanDog() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("kolo", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.TRUE)
        ));
        Assertions.assertTrue(AnimalUtils.isSpiderBitesMoreOftenThanDog(animals));
        animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("kolo", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.TRUE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.TRUE)
        ));
        Assertions.assertFalse(AnimalUtils.isSpiderBitesMoreOftenThanDog(animals));
    }

}
