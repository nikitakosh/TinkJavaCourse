package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task4Test {

    public static Stream<Arguments> providePassword() {
        return Stream.of(
                Arguments.of("wqewrwq", false),
                Arguments.of("", false),
                    Arguments.of("wqdqw~", true),
                Arguments.of("wq!qwc", true),
                Arguments.of("wq@qwc", true),
                Arguments.of("wq#qwc", true),
                Arguments.of("wq$qwc", true),
                Arguments.of("wq%qwc", true),
                Arguments.of("wq^qwc", true),
                Arguments.of("wq&qwc", true),
                Arguments.of("wq*qwc", true),
                Arguments.of("wq|qwc", true),
                Arguments.of("*q|qw@", true)
        );
    }

    @ParameterizedTest
    @MethodSource("providePassword")
    @DisplayName("password check")
    public void isValidPassword(String password, boolean isValid) {
        Assertions.assertEquals(Task4.isValidPassword(password), isValid);
    }
}
