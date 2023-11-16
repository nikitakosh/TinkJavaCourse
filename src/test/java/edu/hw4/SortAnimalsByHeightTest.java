package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SortAnimalsByHeightTest {

    public static Stream<Arguments> provideListAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 60, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 30, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 100, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 40, 10, Boolean.FALSE)
        ));
        List<Animal> sortedAnimals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 30, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 40, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 60, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 100, 10, Boolean.FALSE)
        ));
        return Stream.of(
                Arguments.of(animals, sortedAnimals)
        );
    }

    @ParameterizedTest
    @MethodSource("provideListAnimals")
    @DisplayName("sort animals by height")
    public void sortAnimalsByHeight(List<Animal> animals, List<Animal> sortedAnimals) {
        Assertions.assertEquals(AnimalUtils.sortAnimalsByHeight(animals), sortedAnimals);
    }
}
