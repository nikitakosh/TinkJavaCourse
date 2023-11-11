package edu.hw2.project1_;

import org.jetbrains.annotations.NotNull;

public class Session {
    private final String answer;
    private char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    public Session(String answer, char[] userAnswer, int maxAttempts) {
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.maxAttempts = maxAttempts;
        this.attempts = maxAttempts;
    }

    @NotNull GuessResult guess(char guess) {
        GuessResult guessResult = null;
        if (answer.contains(String.valueOf(guess))){
            guessResult = new GuessResult.SuccessfulGuess(attempts, userAnswer, answer, guess);
        }
        else {
            guessResult = new GuessResult.FailedGuess(attempts, maxAttempts,userAnswer);
        }
        attempts = guessResult.attempt();
        userAnswer = guessResult.state();
        if (attempts == 0){
            guessResult = new GuessResult.Defeat(attempts, userAnswer);
        }
        if (!String.valueOf(userAnswer).contains("*")){
            guessResult = new GuessResult.Win(attempts, userAnswer);
        }
        return guessResult;
    }
}
