package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetCountAnimalsWeightExceedsHeightTest {

    @Test
    @DisplayName("get count animals weight exceeds height")
    public void getCountAnimalsWeightExceedsHeight() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 100, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 70, 80, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getCountAnimalsWeightExceedsHeight(animals), 2);
    }

}
