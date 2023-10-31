package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SortAnimalsByTypeSexNameTest {

    @Test
    @DisplayName("sort animals by type sex name")
    public void sortAnimalsByTypeSexName() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("b", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("b", Animal.Type.FISH, Animal.Sex.F, 5, 70, 10, Boolean.FALSE),
                new Animal("a", Animal.Type.FISH, Animal.Sex.F, 5, 70, 10, Boolean.FALSE),
                new Animal("c", Animal.Type.BIRD, Animal.Sex.F, 5, 70, 10, Boolean.FALSE),
                new Animal("d", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));
        List<Animal> sortedAnimals = new ArrayList<>(List.of(
                new Animal("a", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("b", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("d", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("c", Animal.Type.BIRD, Animal.Sex.F, 5, 70, 10, Boolean.FALSE),
                new Animal("a", Animal.Type.FISH, Animal.Sex.F, 5, 70, 10, Boolean.FALSE),
                new Animal("b", Animal.Type.FISH, Animal.Sex.F, 5, 70, 10, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.sortAnimalsByTypeSexName(animals), sortedAnimals);
    }

}
