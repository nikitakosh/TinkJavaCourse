package edu.hw2.project1_;

import org.jetbrains.annotations.NotNull;

public interface GuessResult {
    char[] state();
    int attempt();
    @NotNull String message();

    record Defeat(int attempts, char[] userAnswer) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public int attempt() {
            return attempts-1;
        }


        @Override
        public @NotNull String message() {
            return "You lost!";
        }
    }
    record Win(int attempts, char[] userAnswer) implements GuessResult {
        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public int attempt() {
            return attempts;
        }

        @Override
        public @NotNull String message() {
            return "You won!";
        }
    }
    record SuccessfulGuess(int attempts, char[] userAnswer, String answer, char guess) implements GuessResult {
        @Override
        public char[] state() {
            for(int i = 0; i < answer.length(); i++){
                if (answer.charAt(i) == guess){
                    userAnswer[i] = guess;
                }
            }
            return userAnswer;
        }

        @Override
        public int attempt() {
            return attempts;
        }

        @Override
        public @NotNull String message() {
            return "Hit\nThe word: " + String.valueOf(userAnswer);
        }
    }
    record FailedGuess(int attempts, int maxAttempts, char[] userAnswer) implements GuessResult {

        @Override
        public char[] state() {
            return userAnswer;
        }

        @Override
        public int attempt() {
            return attempts-1;
        }

        @Override
        public @NotNull String message() {
            return String.format("Missed, mistake %d out of %d.", attempt(), maxAttempts);
        }
    }
}
