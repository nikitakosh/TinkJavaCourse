package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class Task6Test {

    public static Stream<Arguments> provideStringsAndSubstrings() {
        return Stream.of(
                Arguments.of("achfdbaabgabcaabg", "abc", true),
                Arguments.of("cewwefwef", "", true),
                Arguments.of("cewwefwef", "cew", true),
                Arguments.of("cewwefwef", "aaa", false),
                Arguments.of("", "aaa", false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringsAndSubstrings")
    @DisplayName("check substring in string")
    public void isSubstringInString(String str, String substr, boolean ans) {
        Assertions.assertEquals(Task6.isSubstringInString(str, substr), ans);
    }

}
