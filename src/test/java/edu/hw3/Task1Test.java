package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;


public class Task1Test {

    @Test
    @DisplayName("input null test")
    public void inputNullTest(){
        Assertions.assertThrows(NullPointerException.class, () -> Task1.atbash(null));
    }

    public static Stream<Arguments> provideOrigStringAndAtbashString(){
        return Stream.of(
                Arguments.of("Hello world!", "Svool dliow!"),
                Arguments.of("Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler", "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi")
        );
    }
    @ParameterizedTest
    @MethodSource("provideOrigStringAndAtbashString")
    @DisplayName("input strings test")
    public void inputStringsTest(String origStr, String atbashStr){
        Assertions.assertEquals(Task1.atbash(origStr), atbashStr);
    }

    public static Stream<Arguments> provideExtremeLetterAtbashLetter() {
        return Stream.of(
                Arguments.of("A", "Z"),
                Arguments.of("Z", "A"),
                Arguments.of("a", "z"),
                Arguments.of("z", "a")
        );
    }
    @ParameterizedTest
    @MethodSource("provideExtremeLetterAtbashLetter")
    @DisplayName("input extreme letter test")
    public void inputExtremeLetterTest(String origLetter, String atbashLetter){
        Assertions.assertEquals(Task1.atbash(origLetter), atbashLetter);
    }
}
