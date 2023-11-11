package edu.project1;

import edu.project1.exceptions.InvalidWordGivenException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class InputHandler {

    private static final Logger LOGGER = LogManager.getLogger();

    private InputHandler() {
    }

    public static char inputLetter() {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        String playerInput = "";
        while (!validInput) {
            LOGGER.info("Guess a letter (input '!' to give up): ");
            playerInput = scanner.nextLine();
            if (playerInput.length() != 1) {
                LOGGER.info("Input one character");
            } else if (playerInput.charAt(0) == '!') {
                return playerInput.charAt(0);
            } else if (!Character.isLetter(playerInput.charAt(0))) {
                LOGGER.info("You must enter a letter");
            } else {
                validInput = true;
            }
        }
        return playerInput.charAt(0);
    }


    public static int inputPlayerAttempts() {
        Scanner scanner = new Scanner(System.in);
        final int MAX_VALUE_ATTEMPTS = 26;
        int playerAttempts = 0;
        boolean validInput = false;
        while (!validInput) {
            LOGGER.info("Enter the number of attempts: ");
            try {
                playerAttempts = Integer.parseInt(scanner.nextLine());
                if (playerAttempts <= 0 || playerAttempts >= MAX_VALUE_ATTEMPTS) {
                    LOGGER.info("The number of attempts must be greater than 0 and less than 26");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException numberFormatException) {
                LOGGER.info("You must enter a number");
            }
        }
        return playerAttempts;
    }

    public static boolean isFinish() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Do you want to finish the game?(yes or press any key to continue) ");
        String playerInput;
        playerInput = scanner.nextLine();
        return playerInput.equalsIgnoreCase("yes");
    }

    public static String inputWord(Dictionary dictionary) {
        String word = dictionary.randomWord();
        if (word.isEmpty()) {
            throw new InvalidWordGivenException("The hidden word has an incorrect length");
        }
        return word;
    }
}
