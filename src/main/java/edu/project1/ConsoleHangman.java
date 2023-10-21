package edu.project1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();

    public void run(Dictionary dictionary) {
        boolean isFinishGame = false;
        while (!isFinishGame) {
            Session session = getSession(dictionary);
            boolean isFinishSession = false;
            while (!isFinishSession) {
                char letter = InputHandler.inputLetter();
                if (letter == '!') {
                    break;
                }
                GuessResult guessResult = tryGuess(session, letter);
                printState(guessResult);
                if (guessResult.message().equals("You won!") || guessResult.message().equals("You lost!")) {
                    isFinishSession = true;
                }
            }
            isFinishGame = InputHandler.isFinish();
        }

    }

    private GuessResult tryGuess(Session session, char letter) {
        return session.guess(letter);
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());
    }

    private Session getSession(Dictionary dictionary) {
        String answer = InputHandler.inputWord(dictionary);
        int maxAttempts = InputHandler.inputPlayerAttempts();
        char[] userAnswer = "*".repeat(answer.length()).toCharArray();
        return new Session(answer, userAnswer, maxAttempts);
    }
}
