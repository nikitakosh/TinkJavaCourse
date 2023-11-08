package edu.hw5.Task3;

import edu.hw5.Task3.handlers.EghthLocalDateHandler;
import edu.hw5.Task3.handlers.FifthLocalDateHandler;
import edu.hw5.Task3.handlers.FirstLocalDateHandler;
import edu.hw5.Task3.handlers.FourthLocalDateHandler;
import edu.hw5.Task3.handlers.LocalDateHandler;
import edu.hw5.Task3.handlers.NinthLocalDateHandler;
import edu.hw5.Task3.handlers.SecondLocalDateHandler;
import edu.hw5.Task3.handlers.SeventhLocalDateHandler;
import edu.hw5.Task3.handlers.SixthLocalDateHandler;
import edu.hw5.Task3.handlers.ThirdLocalDateHandler;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Task3 {

    private Task3() {
    }

    public static Optional<LocalDate> parseDate(String date) {
        List<LocalDateHandler> localDateHandlers = List.of(
                new FirstLocalDateHandler(),
                new SecondLocalDateHandler(),
                new ThirdLocalDateHandler(),
                new FourthLocalDateHandler(),
                new FifthLocalDateHandler(),
                new SixthLocalDateHandler(),
                new SeventhLocalDateHandler(),
                new EghthLocalDateHandler(),
                new NinthLocalDateHandler()
        );
        return localDateHandlers.stream()
                .filter(localDateHandler -> localDateHandler.canHandledLocalDate(date))
                .map(localDateHandler -> localDateHandler.handle(date))
                .findFirst();
    }
}
