package edu.hw3;

import java.util.Objects;

public class Task1 {
    private Task1() {
    }

    public static String atbash(String origStr) {
        final int START_LOWER_CASE_LETTERS = 65;
        final int END_LOWER_CASE_LETTERS = 90;
        final int START_UPPER_CASE_LETTERS = 97;
        final int END_UPPER_CASE_LETTERS = 122;
        Objects.requireNonNull(origStr);
        StringBuilder atbashStr = new StringBuilder();
        for (char letter : origStr.toCharArray()) {
            if (letter >= START_LOWER_CASE_LETTERS && letter <= END_LOWER_CASE_LETTERS) {
                atbashStr.append((char) (END_LOWER_CASE_LETTERS - (letter - START_LOWER_CASE_LETTERS)));
            } else if (letter >= START_UPPER_CASE_LETTERS && letter <= END_UPPER_CASE_LETTERS) {
                atbashStr.append((char) (END_UPPER_CASE_LETTERS - (letter - START_UPPER_CASE_LETTERS)));
            } else {
                atbashStr.append(letter);
            }
        }
        return String.valueOf(atbashStr);
    }

}
