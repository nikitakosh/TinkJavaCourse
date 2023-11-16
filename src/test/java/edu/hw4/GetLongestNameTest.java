package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetLongestNameTest {


    @Test
    @DisplayName("get longest name")
    public void getLongestName() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bbbbb", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bb", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bbb", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bbbbbbbb", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("b", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));
        Assertions.assertEquals(AnimalUtils.getLongestName(animals), new Animal("bbbbbbbb", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE));
    }
}
