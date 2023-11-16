package edu.hw5.Task3.handlers;

import java.time.LocalDate;

public interface LocalDateHandler {
    boolean canHandledLocalDate(String date);

    LocalDate handle(String date);
}
