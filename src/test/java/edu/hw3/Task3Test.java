package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Task3Test {

    @Test
    @DisplayName("input null test")
    public void inputNullTest(){
        Assertions.assertThrows(NullPointerException.class, () -> Task3.freqDict(null));
    }


    public static Stream<Arguments> provideObjects(){
        String[] strings = new String[] {"hello", "hello", "java", "c", "c"};
        Map<String, Integer> mapStrings = new HashMap<>();
        mapStrings.put("hello", 2);
        mapStrings.put("java", 1);
        mapStrings.put("c", 2);

        Integer[] integers = new Integer[] {1, 1, 2, 2, 2, 3};
        Map<Integer, Integer> mapIntegers = new HashMap<>();
        mapIntegers.put(1, 2);
        mapIntegers.put(2, 3);
        mapIntegers.put(3, 1);

        return Stream.of(
                Arguments.of(strings, mapStrings),
                Arguments.of(integers, mapIntegers)
        );
    }

    @ParameterizedTest
    @MethodSource("provideObjects")
    public void inputObjectsTest(Object[] objects, Map<Object, Integer> map) {
        Assertions.assertEquals(Task3.freqDict(objects), map);
    }
}
