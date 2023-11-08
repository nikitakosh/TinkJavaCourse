package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class Task2Test {

    public static Stream<Arguments> provideYear() {
        return Stream.of(
                Arguments.of("1925", List.of(LocalDate.of(1925, 2, 13), LocalDate.of(1925, 3, 13), LocalDate.of(1925, 11, 13))),
                Arguments.of("2024", List.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideYear")
    @DisplayName("find fridays 13")
    public void findFridays13(String year, List<LocalDate> fridays13) {
        Assertions.assertEquals(Task2.findFridays13(year), fridays13);
    }
}
