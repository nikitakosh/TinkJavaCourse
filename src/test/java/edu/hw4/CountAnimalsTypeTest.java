package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CountAnimalsTypeTest {


    public static Stream<Arguments> provideListAnimals() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
                ));
        Map<Animal.Type, Long> countTypeAnimals = new HashMap<>(
                Map.of(Animal.Type.CAT, 1L, Animal.Type.DOG, 1L, Animal.Type.BIRD, 1L, Animal.Type.SPIDER, 1L, Animal.Type.FISH, 1L)
                );
        return Stream.of(
                Arguments.of(animals, countTypeAnimals)
        );
    }

    @ParameterizedTest
    @MethodSource("provideListAnimals")
    @DisplayName("different type animals")
    public void countDifferentTypeAnimals(List<Animal> animals, Map<Animal.Type, Long> countAnimalsType) {
        Assertions.assertEquals(AnimalUtils.countAnimalsType(animals), countAnimalsType);
    }
}
