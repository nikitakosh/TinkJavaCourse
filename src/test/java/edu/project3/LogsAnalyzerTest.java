package edu.project3;

import edu.project3.exceptions.InputInvalidDateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LogsAnalyzerTest {


    public static Stream<Arguments> provideLogAnalyzer() {
        Map<String, String> params = Map.of(
                "path", "logs/**/2023-08-31.txt",
                "from", "2014-08-23",
                "to", "2019-04-24"
        );
        List<LogRecord> logRecords = List.of(
                new LogRecord("93.180.71.3", "-", LocalDateTime.of(2015, 3, 17, 8, 5, 32),
                        "/downloads/product_1", 200, 100, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", "GET"),
                new LogRecord("93.180.71.3", "-", LocalDateTime.of(2016, 5, 17, 8, 5, 32),
                        "/downloads/product_1", 304, 100, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", "GET"),
                new LogRecord("93.180.71.3", "-", LocalDateTime.of(2017, 5, 17, 8, 5, 32),
                        "/downloads/product_2", 500, 100, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", "POST"),
                new LogRecord("93.180.71.3", "-", LocalDateTime.of(2018, 5, 17, 8, 5, 32),
                        "/downloads/product_2", 500, 100, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", "POST")

        );
        LogsAnalyzer logsAnalyzer = new LogsAnalyzer(params, logRecords);
        return Stream.of(Arguments.of(logsAnalyzer));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("get local date time")
    public void getLocalDateTime(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.getLocalDateTime("2014-08-23"), LocalDateTime.of(2014, 8, 23, 0, 0));
        Assertions.assertThrows(InputInvalidDateException.class, () -> logsAnalyzer.getLocalDateTime("2014/08/23"));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("get paths")
    public void getPaths(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.getPaths("logs/**/2023-08-31.txt"), List.of(Path.of("src/main/resources/project3/logs/2023/2023-08-31.txt")));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("get general info")
    public void getGeneralInfo(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.generalInfo(), Map.of(
                "files", "2023-08-31.txt",
                "from", "23.08.2014",
                "to", "24.04.2019",
                "countLogs", "4",
                "avgResponseSize", "100.0"
        ));
    }


    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("requested resources info")
    public void requestedResourcesInfo(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.requestedResourcesInfo(), Map.of(
                "/downloads/product_1", 2L,
                "/downloads/product_2", 2L
        ));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("status info")
    public void statusInfo(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.statusInfo(), Map.of(
                200, 1L,
                304, 1L,
                500, 2L
        ));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("count user AgentInfo")
    public void countUserAgentInfo(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.countUserAgentInfo(), Map.of(
                "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", 4L
        ));
    }

    @ParameterizedTest
    @MethodSource("provideLogAnalyzer")
    @DisplayName("count request type info")
    public void countRequestTypeInfo(LogsAnalyzer logsAnalyzer) {
        Assertions.assertEquals(logsAnalyzer.countRequestTypeInfo(), Map.of(
                "GET", 2L,
                "POST", 2L
        ));
    }
}
