package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task5Test {

    public static Stream<Arguments> provideLicensePlates() {
        return Stream.of(
                Arguments.of("А123ВЕ777", true),
                Arguments.of("О777ОО177", true),
                Arguments.of("123АВЕ777", false),
                Arguments.of("А123ВГ77", false),
                Arguments.of("А123ВЕ7777", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideLicensePlates")
    @DisplayName("license plates check")
    public void isValidLicensePlates(String licensePlates, boolean isValid) {
        Assertions.assertEquals(Task5.isValidLicensePlates(licensePlates), isValid);
    }

}
