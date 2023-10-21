package edu.project1;

import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private final int maxAttempts;
    private char[] userAnswer;
    private int attempts;

    public Session(String answer, char[] userAnswer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.maxAttempts = maxAttempts;
        this.attempts = maxAttempts;
    }

    public @NotNull GuessResult guess(char guess) {
        GuessResult guessResult;
        if (answer.contains(String.valueOf(guess))) {
            guessResult = new GuessResult.SuccessfulGuess(attempts, userAnswer, answer, guess);
        } else {
            guessResult = new GuessResult.FailedGuess(attempts, maxAttempts, userAnswer);
        }
        attempts = guessResult.attempt();
        userAnswer = guessResult.state();
        if (attempts == 0) {
            guessResult = new GuessResult.Defeat(userAnswer);
        }
        if (!String.valueOf(userAnswer).contains("*")) {
            guessResult = new GuessResult.Win(attempts, userAnswer);
        }
        return guessResult;
    }
}
