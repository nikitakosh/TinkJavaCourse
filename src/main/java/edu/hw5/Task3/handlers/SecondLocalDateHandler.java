package edu.hw5.Task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SecondLocalDateHandler implements LocalDateHandler {
    private static final String PATTERN = "yyyy-M-d";

    @Override
    public boolean canHandledLocalDate(String date) {
        return date.contains("-") && date.split("-")[2].length() == 1;
    }

    @Override
    public LocalDate handle(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDate.parse(date, formatter);
    }

}
