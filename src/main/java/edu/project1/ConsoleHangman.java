package edu.hw2.project1_;

import java.util.List;

public class ConsoleHangman {
    public void run() {
        boolean isFinishGame = false;
        while (!isFinishGame){
            Session session = getSession();
            boolean isFinishSession = false;
            while (!isFinishSession) {
                char letter = InputHandler.inputLetter();
                GuessResult guessResult = tryGuess(session, letter);
                printState(guessResult);
                if (guessResult.message().equals("You won!") || guessResult.message().equals("You lost!")){
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
        System.out.println(guess.message());
    }

    private Session getSession(){
        int maxAttempts = InputHandler.inputPlayerAttempts();
        Dictionary dictionary = new MyDictionary(List.of("hello", "circle", "world"));
        String answer = dictionary.randomWord();
        char[] userAnswer = "*".repeat(answer.length()).toCharArray();
        return new Session(answer, userAnswer, maxAttempts);
    }
}
