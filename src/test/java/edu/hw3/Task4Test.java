package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

public class Task4Test {

    public static Stream<Arguments> provideNumber() {
        return Stream.of(
                Arguments.of(2, "II"),
                Arguments.of(12, "XII"),
                Arguments.of(16, "XVI"),
                Arguments.of(14, "XIV"),
                Arguments.of(35, "XXXV"),
                Arguments.of(81, "LXXXI"),
                Arguments.of(3393, "MMMCCCXCIII"),
                Arguments.of(3294, "MMMCCXCIV")

        );
    }

    @ParameterizedTest
    @ValueSource(ints = {-12, 0, 4000, 4001})
    @DisplayName("input illegal numbers")
    public void inputIllegalNumbers(int illegalNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Task4.convertToRoman(illegalNumber));
    }

    @ParameterizedTest
    @MethodSource("provideNumber")
    @DisplayName("input different number")
    public void inputDifferentNumber(int arabNumber, String romanNumber) {
        Assertions.assertEquals(Task4.convertToRoman(arabNumber), romanNumber);
    }
}
