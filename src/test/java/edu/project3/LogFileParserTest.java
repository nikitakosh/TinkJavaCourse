package edu.project3;

import edu.project3.parsers.LogFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class LogFileParserTest {


    @Test
    @DisplayName("parse log files")
    public void parseLogFiles() {
        LogFileParser logFileParser = new LogFileParser("logs*");
        Assertions.assertEquals(logFileParser.parseLogFiles(), List.of(
                Path.of("src/main/resources/project3/logs/2021/2021-08-31.log"),
                Path.of("src/main/resources/project3/logs/2022/2022-08-31.log"),
                Path.of("src/main/resources/project3/logs/2023/2023-08-31.txt"),
                Path.of("src/main/resources/project3/logs/2024/2024-08-31.txt")
        ));
        logFileParser = new LogFileParser("logs/**/2023-08-31.txt");
        Assertions.assertEquals(logFileParser.parseLogFiles(), List.of(
                Path.of("src/main/resources/project3/logs/2023/2023-08-31.txt")
        ));
    }

    @Test
    @DisplayName("parse LogRecord")
    public void parseLogRecord() {
        LogFileParser logFileParser = new LogFileParser("logs/2021*");
        Assertions.assertEquals(logFileParser.parseLogRecord(), List.of(
                new LogRecord("70.168.108.194", "-", LocalDateTime.of(2015, 6, 4, 4, 6, 0),
                        "/downloads/product_2", 200, 951, "-", "urlgrabber/3.9.1 yum/3.2.29", "GET"),
                new LogRecord("67.132.206.254", "-", LocalDateTime.of(2015, 6, 4, 4, 6, 23),
                        "/downloads/product_1", 200, 2592, "-", "urlgrabber/3.1.0 yum/3.2.22", "GET"))
        );
    }

}
