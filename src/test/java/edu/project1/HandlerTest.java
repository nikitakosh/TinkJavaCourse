package edu.project1;

import org.junit.Test;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import static edu.project1.InputHandler.inputLetter;

public class HandlerTest {
    private ByteArrayInputStream inputStream;


    @Test
    @DisplayName("input correct letter test")
    public void inputLetterTest(){
        inputStream = new ByteArrayInputStream("a\n".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputLetter(), 'a');
    }
    @Test
    @DisplayName("input '!' test")
    public void giveUpTest(){
        inputStream = new ByteArrayInputStream("!\n".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputLetter(), '!');
    }

    @Test
    @DisplayName("input empty than input correct letter test")
    public void inputEmptyTest(){
        inputStream = new ByteArrayInputStream("\ne".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputLetter(), 'e');
    }

    @Test
    @DisplayName("input more than one letter than input one letter test")
    public void inputMoreThanOneLetterTest(){
        inputStream = new ByteArrayInputStream("qq\ne".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(inputLetter(), 'e');
    }

    @Test
    @DisplayName("input less than zero attempts than input correct attempts test")
    public void inputLessThanZeroAttemptsTest(){
        inputStream = new ByteArrayInputStream("-1\n2".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputPlayerAttempts(), 2);
    }
    @Test
    @DisplayName("input grater than 26 attempts than input correct attempts test")
    public void inputGreaterThan26AttemptsTest(){
        inputStream = new ByteArrayInputStream("27\n2".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputPlayerAttempts(), 2);
    }
    @Test
    @DisplayName("input not digits than input correct attempts test")
    public void inputNotDigitsTest(){
        inputStream = new ByteArrayInputStream("d\n2".getBytes());
        System.setIn(inputStream);
        Assertions.assertEquals(InputHandler.inputPlayerAttempts(), 2);
    }
}
