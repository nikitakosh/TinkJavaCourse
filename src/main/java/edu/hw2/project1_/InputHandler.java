package edu.hw2.project1_;

import org.apache.logging.log4j.core.appender.rolling.AbstractTriggeringPolicy;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static char inputLetter(){
        boolean validInput = false;
        String playerInput = "";
        while (!validInput){
            System.out.print("Guess a letter: ");
            playerInput = scanner.nextLine();
            if (playerInput.length() != 1){
                System.out.println("Input one character");
            } else if (!Character.isLetter(playerInput.charAt(0))) {
                System.out.println("You must enter a letter");
            } else {
                validInput = true;
            }
        }
        return playerInput.charAt(0);
    }

    public static String inputPlayerName() {
        String playerName = "";
        boolean validInput = false;
        while(!validInput){
            System.out.print("What is your name? ");
            playerName = scanner.nextLine();
            if (!playerName.isEmpty()){
                validInput = true;
            }
        }
        return playerName;
    }

    public static int inputPlayerAttempts() {
        int playerAttempts = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.print("Enter the number of attempts: ");
            try {
                playerAttempts = Integer.parseInt(scanner.nextLine());
                if (playerAttempts <= 0){
                    System.out.println("The number of attempts must be greater than 0");
                } else{
                    validInput = true;
                }
            } catch (NumberFormatException e1){
                System.out.println("You must enter a number");
            }
        }
        return playerAttempts;
    }

    public static boolean isFinish() {
        System.out.print("Do you want to finish the game?(yes or press any key to continue) ");
        String playerInput = "";
        playerInput = scanner.nextLine();
        return playerInput.equalsIgnoreCase("yes");
    }
}
