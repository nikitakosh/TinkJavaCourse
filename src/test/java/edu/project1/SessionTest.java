package edu.project1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {


    @Test
    @DisplayName("test limit of attempts exceeded")
    public void limitOfAttemptsExceededTest() {
        Session session = new Session("hello", "*****".toCharArray(), 2);
        session.guess('q');
        GuessResult.Defeat defeat = new GuessResult.Defeat("*****".toCharArray());
        Assertions.assertInstanceOf(session.guess('g').getClass(), defeat);
    }

    @Test
    @DisplayName("input existing letter test")
    public void existingLetterTest(){
        Session session = new Session("hello", "*****".toCharArray(), 2);
        GuessResult.SuccessfulGuess successfulGuess = new GuessResult.SuccessfulGuess(2, "*e***".toCharArray(), "hello", 'e');
        Assertions.assertInstanceOf(session.guess('e').getClass(), successfulGuess);
    }
    @Test
    @DisplayName("input not existing letter test")
    public void notExistingLetterTest(){
        Session session = new Session("hello", "*****".toCharArray(), 2);
        GuessResult.FailedGuess failedGuess = new GuessResult.FailedGuess(  1, 2, "*****".toCharArray());
        Assertions.assertInstanceOf(session.guess('q').getClass(), failedGuess);
    }

    @Test
    @DisplayName("input last existing letter in word test")
    public void inputLastExistingLetterInWordTest(){
        Session session = new Session("hello", "*****".toCharArray(), 2);
        session.guess('h');
        session.guess('e');
        session.guess('l');
        GuessResult.Win win = new GuessResult.Win(2, "hello".toCharArray());
        Assertions.assertInstanceOf(session.guess('o').getClass(), win);
    }
}
