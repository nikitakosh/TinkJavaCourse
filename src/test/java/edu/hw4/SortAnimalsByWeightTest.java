package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SortAnimalsByWeightTest {

    public static Stream<Arguments> provideListAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 70, 20, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 5, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 30, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 25, Boolean.FALSE)
        ));
        List<Animal> freeHardestAnimals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 30, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 25, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 70, 20, Boolean.FALSE)
        ));
        List<Animal> twoHardestAnimals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 30, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 25, Boolean.FALSE)
        ));
        return Stream.of(
                Arguments.of(animals, freeHardestAnimals, 3),
                Arguments.of(animals, twoHardestAnimals, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("provideListAnimals")
    @DisplayName("sort animals by weight")
public void sortAnimalsByWeight(List<Animal> animals, List<Animal> kHardestAnimals, int k) {
        Assertions.assertEquals(AnimalUtils.sortAnimalsByWeight(animals, k), kHardestAnimals);
    }
}
