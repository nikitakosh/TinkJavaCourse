package edu.hw5.Task3.handlers;

import java.time.LocalDate;
import java.util.Objects;

public class FifthLocalDateHandler implements LocalDateHandler {

    @Override
    public boolean canHandledLocalDate(String date) {
        return Objects.equals(date, "tomorrow");
    }

    @Override
    public LocalDate handle(String date) {
        return LocalDate.now().plusDays(1);
    }

}
