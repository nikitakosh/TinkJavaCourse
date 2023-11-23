package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task1Test {


    public static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1213),
                Arguments.of(3123),
                Arguments.of(13),
                Arguments.of(1321213),
                Arguments.of(112)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    @DisplayName("increase number")
    public void increaseNumber(int increaseValue) {
        Assertions.assertEquals(Task1.increaseNumber(increaseValue), increaseValue);
    }
}
