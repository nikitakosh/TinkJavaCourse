package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Task2Test {


    @Test
    @DisplayName("input null test")
    public void inputNullTest(){
        Assertions.assertThrows(NullPointerException.class, () -> Task2.clusterize(null));
    }

    public static Stream<Arguments> provideBracketSequences(){
        return Stream.of(
                Arguments.of(
                        "()()()",
                        new LinkedList<>(List.of(new StringBuilder("()"), new StringBuilder("()"), new StringBuilder("()")))
                ),
                Arguments.of(
                        "((()))",
                        new LinkedList<>(List.of(new StringBuilder("((()))")))
                ),
                Arguments.of(
                        "((()))(())()()(()())",
                        new LinkedList<>(List.of(new StringBuilder("((()))"), new StringBuilder("(())"), new StringBuilder("()"), new StringBuilder("()"), new StringBuilder("(()())")))
                ),
                Arguments.of("((())())(()(()()))",
                        new LinkedList<>(List.of(new StringBuilder("((())())"), new StringBuilder("(()(()()))")))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBracketSequences")
    public void inputBracketSequence(String bracketSequence, List<StringBuilder> bracketClusters){
        Assertions.assertInstanceOf(Task2.clusterize(bracketSequence).getClass(), bracketClusters);
    }
}
