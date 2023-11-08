package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task1Test {

    public static Stream<Arguments> provideTimes() {
        return Stream.of(
                Arguments.of(
                        new String[] {"2022-03-12, 20:20 - 2022-03-12, 23:50", "2022-04-01, 21:30 - 2022-04-02, 01:20"},
                        "3ч 40м"
                ),
                Arguments.of(
                        new String[] {"2022-03-12, 01:00 - 2022-03-12, 02:00", "2022-04-01, 02:00 - 2022-04-01, 03:00", "2022-04-01, 02:00 - 2022-04-01, 03:00"},
                        "1ч 0м"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideTimes")
    @DisplayName("calculate average time")
    public void calculateAverageTime(String[] times, String avgTime) {
        Assertions.assertEquals(Task1.calculateAverageTime(times), avgTime);
    }

    @Test
    @DisplayName("input invalid time")
    public void inputInvalidTime() {
        Assertions.assertEquals(Task1.calculateAverageTime(new String[] {"2022-03-12, 20:20 2022-03-12, 23:50"}), "invalid time");
    }

}
