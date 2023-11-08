package edu.hw5;

import edu.hw5.Task3.Task3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;

import java.util.Optional;
import java.util.stream.Stream;

public class Task3Test {

    public static Stream<Arguments> provideDate() {
        return Stream.of(
                Arguments.of("2020-10-10", Optional.of(LocalDate.of(2020, 10, 10))),
                Arguments.of("2020-12-2", Optional.of(LocalDate.of(2020, 12, 2))),
                Arguments.of("1/3/1976", Optional.of(LocalDate.of(1976, 3, 1))),
                Arguments.of("1/3/20", Optional.of(LocalDate.of(2020, 3, 1))),
                Arguments.of("tomorrow", Optional.of(LocalDate.now().plusDays(1))),
                Arguments.of("today", Optional.of(LocalDate.now())),
                Arguments.of("yesterday", Optional.of(LocalDate.now().minusDays(1))),
                Arguments.of("1 day ago", Optional.of(LocalDate.now().minusDays(1))),
                Arguments.of("2234 days ago", Optional.of(LocalDate.now().minusDays(2234)))

        );
    }

    @ParameterizedTest
    @MethodSource("provideDate")
    @DisplayName("parse date")
    public void parseData(String date, Optional<LocalDate> expectedLocalDate) {
        Assertions.assertEquals(Task3.parseDate(date), expectedLocalDate);
    }

    @Test
    @DisplayName("parse invalid date")
    public void parseInvalidDate() {
        Assertions.assertEquals(Task3.parseDate("friday"), Optional.empty());
    }
}
