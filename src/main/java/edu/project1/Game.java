//package edu.project1;
//
//import edu.hw2.project1_.Dictionary;
//import java.util.Objects;
//
//public class Game {
//    private final Dictionary dictionary;
//    private int maxAttempts;
//    private String word;
//    private Player player;
//
//    public Game(Dictionary dictionary) {
//        this.dictionary = dictionary;
//    }
//
//    public String outputGuessedLetter(String word, String currGuessedWord, char letter) {
//        StringBuilder newCurrGuessedWord = new StringBuilder(currGuessedWord);
//        for (int i = 0; i < word.length(); i++) {
//            if (word.charAt(i) == letter) {
//                newCurrGuessedWord.setCharAt(i, letter);
//            }
//        }
//        return String.valueOf(newCurrGuessedWord);
//    }
//
//    private void round(String guessedWord) {
//        char letter = InputHandler.inputLetter(guessedWord);
//        if (word.contains(String.valueOf(letter))) {
//            System.out.println("Hit!");
//            String currGuessedWord = outputGuessedLetter(word, guessedWord, letter);
//            System.out.println("The word: " + currGuessedWord);
//            if (Objects.equals(currGuessedWord, word)) {
//                System.out.println("You won!");
//            } else {
//                round(currGuessedWord);
//            }
//        } else {
//            player.setAttempts(player.getAttempts() - 1);
//            if (player.getAttempts() == 0) {
//                System.out.println("You lost!");
//            } else {
//                System.out.printf("Missed, mistake %d out of %d.%n", player.getAttempts(), maxAttempts);
//                round(guessedWord);
//            }
//        }
//    }
//
//    public void startGame() {
//        configurationGame();
//        round("*".repeat(word.length()));
//    }
//
//    private void configurationGame() {
//        String playerName = InputHandler.inputPlayerName();
//        int playerAttempts = InputHandler.inputPlayerAttempts();
//        this.player = new Player(playerAttempts, playerName);
//        this.maxAttempts = playerAttempts;
//        word = dictionary.getRandomWord();
//    }
//}
