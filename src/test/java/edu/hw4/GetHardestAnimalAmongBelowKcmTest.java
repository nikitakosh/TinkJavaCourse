package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetHardestAnimalAmongBelowKcmTest {

    @Test
    @DisplayName("get hardest animal among below k cm")
    public void getHardestAnimalAmongBelowKcm() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 30, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 80, 20, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 90, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 100, 40, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 110, 80, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getHardestAnimalAmongBelowKcm(animals, 100).orElse(null), new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 30, Boolean.FALSE));
    }

}
