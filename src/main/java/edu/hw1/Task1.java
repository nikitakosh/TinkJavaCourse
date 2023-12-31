package edu.hw1;

import java.util.Objects;

public class Task1 {

    private Task1() {
    }

    private static final int BASE = 60;

    public static int minutesToSeconds(String time) {
        Objects.requireNonNull(time);
        int minutes = Integer.parseInt(time.split(":")[0]);
        int seconds = Integer.parseInt(time.split(":")[1]);
        if (seconds >= BASE) {
            return -1;
        }
        return minutes * BASE + seconds;
    }
}
