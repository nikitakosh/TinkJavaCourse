package edu.project1;

import java.util.List;

public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        ConsoleHangman consoleHangman = new ConsoleHangman();
        Dictionary dictionary = new HangmanDictionary(List.of("hello", "java", "world"));
        consoleHangman.run(dictionary);
    }
}
