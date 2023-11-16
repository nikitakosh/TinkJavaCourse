package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Task8Test {


    public static Stream<Arguments> provideListsWithDifferentTypes() {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3));
        List<String> strings = new ArrayList<>(List.of("abc", "abcd", "abcde"));
        List<Character> characters = new ArrayList<>(List.of('a', 'b', 'c'));
        return Stream.of(
                Arguments.of(nums, 3),
                Arguments.of(strings, "abcde"),
                Arguments.of(characters, 'c')
        );
    }

    @Test
    @DisplayName("empty list")
    public void emptyList() {
        BackwardIterator<Integer> backwardIterator = new BackwardIterator<>(Collections.emptyList());
        Assertions.assertFalse(backwardIterator.hasNext());
    }

    @ParameterizedTest
    @MethodSource("provideListsWithDifferentTypes")
    @DisplayName("lists with different types")
    public void listsWithDifferentTypes(List<Object> objects, Object firstElement) {
        BackwardIterator<Object> backwardIterator = new BackwardIterator<>(objects);
        Assertions.assertEquals(backwardIterator.next(), firstElement);
    }
}
