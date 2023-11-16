package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task1 {

    public static final String YYYY_MM_D_H_M = "yyyy-MM-d, H:m";
    public static final String SPLIT_REGEX = " - ";
    public static final int HOUR_DURATION = 3600;
    public static final int MINUTE_DURATION = 60;

    private Task1() {
    }

    public static String calculateAverageTime(String[] times) {
        long sumSeconds = 0;
        for (String time : times) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_D_H_M);
            try {
                LocalDateTime timeStart = LocalDateTime.parse(time.split(SPLIT_REGEX)[0], formatter);
                LocalDateTime timeFinish = LocalDateTime.parse(time.split(SPLIT_REGEX)[1], formatter);
                sumSeconds += Duration.between(timeStart, timeFinish).getSeconds();
            } catch (DateTimeParseException e) {
                return "invalid time";
            }
        }
        long avgSeconds = sumSeconds / times.length;
        return avgSeconds / HOUR_DURATION + "ч " + avgSeconds % HOUR_DURATION / MINUTE_DURATION + "м";
    }
}
