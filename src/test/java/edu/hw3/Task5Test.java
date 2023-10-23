package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class Task5Test {

    @Test
    @DisplayName("input null and empty list")
    public void inputNullAndEmptyList(){
        Assertions.assertArrayEquals(Task5.parseContacts(null, "ASC"), new String[]{});
        Assertions.assertArrayEquals(Task5.parseContacts(new String[]{}, "ASC"), new String[]{});
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "asdsa", "qq"})
    public void invalidSortType(String invalidSortType) {
        Assertions.assertThrows(InvalidSortTypeException.class, () -> Task5.parseContacts(new String[]{}, invalidSortType));
    }


    public static Stream<Arguments> provideStringArraysASC() {
        String[] array1 = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String[] array2 = new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        String[] sortedArray1ASC = new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume", "John Locke"};
        String[] sortedArray2ASC = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        return Stream.of(
                Arguments.of(array1, sortedArray1ASC),
                Arguments.of(array2, sortedArray2ASC)
        );
    }
    @ParameterizedTest
    @MethodSource("provideStringArraysASC")
    @DisplayName("input string array and sort them ASC")
    public void sortASC(String[] inputArray, String[] sortedArrayASC){
        Assertions.assertArrayEquals(Task5.parseContacts(inputArray, "ASC"), sortedArrayASC);
    }
    public static Stream<Arguments> provideStringArraysDESC() {
        String[] array1 = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String[] array2 = new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        String[] sortedArray1DESC = new String[] {"John Locke", "David Hume", "Rene Descartes", "Thomas Aquinas"};
        String[] sortedArray2DESC = new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"};
        return Stream.of(
                Arguments.of(array1, sortedArray1DESC),
                Arguments.of(array2, sortedArray2DESC)
        );
    }
    @ParameterizedTest
    @MethodSource("provideStringArraysDESC")
    @DisplayName("input string array and sort them DESC")
    public void sortDESC(String[] inputArray, String[] sortedArrayDESC){
        Assertions.assertArrayEquals(Task5.parseContacts(inputArray, "DESC"), sortedArrayDESC);
    }
}
