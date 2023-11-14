package edu.hw5.Task3.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ThirdLocalDateHandler implements LocalDateHandler {
    private static final String PATTERN = "d/M/yyyy";
    public static final int YEAR_LENGTH = 4;

    @Override
    public boolean canHandledLocalDate(String date) {
        return date.contains("/") && date.split("/")[2].length() == YEAR_LENGTH;
    }

    @Override
    public LocalDate handle(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
        return LocalDate.parse(date, formatter);
    }

}
