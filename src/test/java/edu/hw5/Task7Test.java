package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task7Test {


    public static Stream<Arguments> provideStringsForRegex1() {
        return Stream.of(
                Arguments.of("000", true),
                Arguments.of("010", true),
                Arguments.of("01", false),
                Arguments.of("001", false),
                Arguments.of("001010", false),
                Arguments.of("0110 ", false),
                Arguments.of("0101  ", true)

        );
    }

    public static Stream<Arguments> provideStringsForRegex2() {
        return Stream.of(
                Arguments.of("10010101", true),
                Arguments.of("10010100", false)
        );
    }

    public static Stream<Arguments> provideStringsForRegex3() {
        return Stream.of(
                Arguments.of("1", true),
                Arguments.of("10", true),
                Arguments.of("101", true),
                Arguments.of("1010", false),
                Arguments.of("", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex1")
    @DisplayName("check regex1")
    public void regex1(String str, boolean ans) {
        Assertions.assertEquals(Task7.regex1(str), ans);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex2")
    @DisplayName("check regex2")
    public void regex2(String str, boolean ans) {
        Assertions.assertEquals(Task7.regex2(str), ans);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex3")
    @DisplayName("check regex3")
    public void regex3(String str, boolean ans) {
        Assertions.assertEquals(Task7.regex3(str), ans);
    }
}
