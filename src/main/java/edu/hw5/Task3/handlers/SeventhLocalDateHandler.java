package edu.hw5.Task3.handlers;

import java.time.LocalDate;
import java.util.Objects;

public class SeventhLocalDateHandler implements LocalDateHandler {

    @Override
    public boolean canHandledLocalDate(String date) {
        return Objects.equals(date, "yesterday");
    }

    @Override
    public LocalDate handle(String date) {
        return LocalDate.now().minusDays(1);
    }

}
