package edu.hw5.Task3.handlers;

import java.time.LocalDate;

public class NinthLocalDateHandler implements LocalDateHandler {

    public static final int DATE_LENGTH = 3;

    @Override
    public boolean canHandledLocalDate(String date) {
        return date.split(" ").length == DATE_LENGTH && date.split(" ")[1].equals("days");
    }

    @Override
    public LocalDate handle(String date) {
        return LocalDate.now().minusDays(Long.parseLong(date.split(" ")[0]));
    }

}
