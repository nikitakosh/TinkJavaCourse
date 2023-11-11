package edu.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GetAnimalsNameLongerTwoWordsTest {

    @Test
    @DisplayName("get animals name longer two words")
    public void getAnimalsNameLongerTwoWordsTest() {
        List<Animal> animals = new ArrayList<>(List.of(
                new Animal("bob bob bob bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("kolo", Animal.Type.DOG, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.BIRD, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("bob", Animal.Type.SPIDER, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("tom tom tom", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));
        List<Animal> animalsNameLongerTwoWords = new ArrayList<>(List.of(
                new Animal("bob bob bob bob", Animal.Type.CAT, Animal.Sex.M, 5, 70, 10, Boolean.FALSE),
                new Animal("tom tom tom", Animal.Type.FISH, Animal.Sex.M, 5, 70, 10, Boolean.FALSE)
        ));

        Assertions.assertEquals(AnimalUtils.getAnimalsNameLongerTwoWords(animals), animalsNameLongerTwoWords);
    }
}
