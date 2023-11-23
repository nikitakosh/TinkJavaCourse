package edu.hw7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task2Test {

    public static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(5, 120L),
                Arguments.of(10, 3628800L),
                Arguments.of(15, 1307674368000L),
                Arguments.of(17, 355687428096000L),
                Arguments.of(20, 2432902008176640000L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    @DisplayName("factorial calculation")
        public void factorial(int number, Long factorial) {
        Assertions.assertEquals(Task2.factorial(number), factorial);
    }

}
