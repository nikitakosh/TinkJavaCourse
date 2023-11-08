package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task8Test {

    public static Stream<Arguments> provideStringsForRegex1() {
        return Stream.of(
                Arguments.of("", false),
                Arguments.of("1", true),
                Arguments.of("10", false),
                Arguments.of("101", true)
        );
    }

    public static Stream<Arguments> provideStringsForRegex2() {
        return Stream.of(
                Arguments.of("001", true),
                Arguments.of("1010", true),
                Arguments.of("0010", false),
                Arguments.of("101", false)
        );
    }

    public static Stream<Arguments> provideStringsForRegex3() {
        return Stream.of(
                Arguments.of("000", true),
                Arguments.of("010101", true),
                Arguments.of("0101011101010", true),
                Arguments.of("010101110101", false),
                Arguments.of("", true),
                Arguments.of("0000", false)
        );
    }

    public static Stream<Arguments> provideStringsForRegex4() {
        return Stream.of(
                Arguments.of("11", false),
                Arguments.of("111", false),
                Arguments.of("1111", true),
                Arguments.of("0111", true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex1")
    @DisplayName("check regex1")
    public void regex1(String str, boolean ans) {
        Assertions.assertEquals(Task8.regex1(str), ans);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex2")
    @DisplayName("check regex2")
    public void regex2(String str, boolean ans) {
        Assertions.assertEquals(Task8.regex2(str), ans);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex3")
    @DisplayName("check regex3")
    public void regex3(String str, boolean ans) {
        Assertions.assertEquals(Task8.regex3(str), ans);
    }

    @ParameterizedTest
    @MethodSource("provideStringsForRegex4")
    @DisplayName("check regex4")
    public void regex4(String str, boolean ans) {
        Assertions.assertEquals(Task8.regex4(str), ans);
    }
}
