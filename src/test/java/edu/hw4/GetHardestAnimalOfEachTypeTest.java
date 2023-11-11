package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetHardestAnimalOfEachTypeTest {

    @Test
    @DisplayName("get hardest animal of each type")
    public void getHardestAnimalOfEachType() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.CAT, Animal.Sex.M, 5, 70, 20, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 30, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 40, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 50, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 60, Boolean.FALSE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 70, Boolean.FALSE),
                new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 80, Boolean.FALSE)
        ));
        Map<Animal.Type, Animal> hardestAnimalOfEachType = new HashMap<>(Map.of(
                Animal.Type.CAT, new Animal("kolo", Animal.Type.CAT, Animal.Sex.M, 5, 70, 20, Boolean.FALSE),
                Animal.Type.BIRD, new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 40, Boolean.FALSE),
                Animal.Type.FISH, new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 60, Boolean.FALSE),
                Animal.Type.DOG, new Animal("bob", Animal.Type.DOG, Animal.Sex.M, 5, 70, 80, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getHardestAnimalOfEachType(animals), hardestAnimalOfEachType);
    }
}
