package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetSumWeightAnimalsHeightFromKcmToLcmTest {

    @Test
    @DisplayName("get sum weight animals height from K cm to L cm")
    public void getSumWeightAnimalsHeightFromKcmToLcm() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 30, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 40, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 50, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 60, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getSumWeightAnimalsHeightFromKcmToLcm(animals, 40, 60), 30);
    }

}
