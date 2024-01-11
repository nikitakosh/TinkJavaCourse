package edu.project3;

import edu.project3.exceptions.InputInvalidDateException;
import edu.project3.handlers.PathHandler;
import edu.project3.parsers.LogFileParser;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class LogsAnalyzer {
    public static final String FROM_TIME = "from";
    public static final String TO_TIME = "to";
    public static final String FILES = "files";
    public static final String COUNT_LOGS = "countLogs";
    public static final String AVG_RESPONSE_SIZE = "avgResponseSize";
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final Map<String, String> generalInfo = new HashMap<>();
    private final List<LogRecord> logRecordsBetweenFromAndTo;
    private final List<Path> paths;

    public LogsAnalyzer(Map<String, String> params, List<LogRecord> logRecords) {
        this.from = getLocalDateTime(params.get(FROM_TIME));
        this.to = getLocalDateTime(params.get(TO_TIME));
        this.logRecordsBetweenFromAndTo = getLogRecordsBetweenFromAndTo(logRecords);
        this.paths = getPaths(params.get("path"));
    }


    public LocalDateTime getLocalDateTime(String time) {
        LocalDateTime localDateTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm", Locale.ENGLISH);
        if (!time.isEmpty()) {
            try {
                localDateTime = LocalDateTime.parse(time + ":00:00", dateTimeFormatter);
            } catch (DateTimeParseException e) {
                throw new InputInvalidDateException("input invalid date, please input date in pattern yyyy-MM-dd");
            }
        } else {
            localDateTime = null;
        }
        return localDateTime;
    }

    private List<LogRecord> getLogRecordsBetweenFromAndTo(List<LogRecord> logRecords) {
        if (from != null && to != null) {
            return logRecords.stream()
                    .filter(logRecord -> logRecord.timeLocal().isAfter(from) && logRecord.timeLocal().isBefore(to))
                    .toList();
        } else if (from != null) {
            return logRecords.stream()
                    .filter(logRecord -> logRecord.timeLocal().isAfter(from))
                    .toList();
        } else if (to != null) {
            return logRecords.stream()
                    .filter(logRecord -> logRecord.timeLocal().isBefore(to))
                    .toList();
        }
        return logRecords;
    }

    public List<Path> getPaths(String path) {
        if (!new PathHandler().isHttpPath(path)) {
            return new LogFileParser(path).parseLogFiles();
        }
        return List.of();
    }


    public Map<String, String> generalInfo() {
        DateTimeFormatter dateTimeFormatterForInfo = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.ENGLISH);
        if (from != null) {
            generalInfo.put(FROM_TIME, from.format(dateTimeFormatterForInfo));
        } else {
            generalInfo.put(FROM_TIME, "-");
        }
        if (to != null) {
            generalInfo.put(TO_TIME, to.format(dateTimeFormatterForInfo));
        } else {
            generalInfo.put(TO_TIME, "-");
        }
        if (!paths.isEmpty()) {
            String pathsString = paths.stream()
                    .map(path -> path.toFile().getName())
                    .collect(Collectors.joining(" "));
            generalInfo.put(FILES, pathsString);
        } else {
            generalInfo.put(FILES, "-");
        }
        generalInfo.put(COUNT_LOGS, String.valueOf(logRecordsBetweenFromAndTo.size()));
        generalInfo.put(AVG_RESPONSE_SIZE, String.valueOf(logRecordsBetweenFromAndTo.stream()
                .mapToLong(LogRecord::bodyBytesSent)
                .average()
                .orElse(0L)));
        return generalInfo;
    }

    public Map<String, Long> requestedResourcesInfo() {
        return logRecordsBetweenFromAndTo.stream()
                .map(LogRecord::request)
                .collect(Collectors.groupingBy(request -> request, Collectors.counting()));
    }

    public Map<Integer, Long> statusInfo() {
        return logRecordsBetweenFromAndTo.stream()
                .map(LogRecord::status)
                .collect(Collectors.groupingBy(status -> status, Collectors.counting()));
    }

    public Map<String, Long> countUserAgentInfo() {
        return logRecordsBetweenFromAndTo.stream()
                .map(LogRecord::httpUserAgent)
                .collect(Collectors.groupingBy(
                        httpUserAgent -> httpUserAgent,
                        Collectors.counting()
                ));
    }

    public Map<String, Long> countRequestTypeInfo() {
        return logRecordsBetweenFromAndTo.stream()
                .map(LogRecord::requestType)
                .collect(Collectors.groupingBy(
                        requestType -> requestType,
                        Collectors.counting()
                ));
    }
}
