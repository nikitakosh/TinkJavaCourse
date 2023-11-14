package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    public static final int DAY_OF_MONTH_13 = 13;

    private Task2() {
    }

    public static final String DD_MM_YYYY = "dd.MM.yyyy";
    public static final String START_DATE = "13.01.";
    public static final int MAX_MONTH = 12;

    public static List<LocalDate> findFridays13(String year) {
        List<LocalDate> fridays13 = new ArrayList<>();
        String startDate = START_DATE + year;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
        LocalDate localDate = LocalDate.parse(startDate, dateTimeFormatter);
        while (localDate.getMonthValue() != MAX_MONTH) {
            if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(localDate);
            }
            localDate = localDate.with(TemporalAdjusters.ofDateAdjuster(date ->
                    date.plusMonths(1)));
        }
        if (localDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
            fridays13.add(localDate);
        }
        return fridays13;
    }

    public static LocalDate findFriday13(LocalDate date) {
        LocalDate currDate = date;
        if (currDate.getDayOfMonth() > DAY_OF_MONTH_13) {
            currDate = currDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(DAY_OF_MONTH_13);
        } else {
            currDate = currDate.plusDays(DAY_OF_MONTH_13 - currDate.getDayOfMonth());
        }
        while (currDate.getDayOfWeek() != DayOfWeek.FRIDAY) {
            currDate = currDate.plusMonths(1);
        }
        return currDate;
    }
}
