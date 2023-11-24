package edu.project3.utils;

import edu.project3.LogRecord;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogRecordParserUtils {

    public static final String RECORD_PATTERN =
            "^(\\S+) - (\\S+) \\[(\\S+ \\+0000)] \"(\\S+) (\\S+) (\\S+)\" (\\d+) (\\d+) \"(\\S+)\" \"(.*)\"$";
    public static final int GROUP_DATE_TIME = 3;
    public static final int GROUP_REMOTE_ADDR = 1;
    public static final int GROUP_REMOTE_USER = 2;
    public static final int GROUP_REQUEST = 5;
    public static final int GROUP_STATUS = 7;
    public static final int GROUP_BODY_BYTES_SENT = 8;
    public static final int GROUP_HTTP_REFER = 9;
    public static final int GROUP_HTTP_USER_AGENT = 10;
    public static final int GROUP_REQUEST_TYPE = 4;

    private LogRecordParserUtils() {}

    public static LogRecord parse(String logEntry) {
        Pattern recordPattern = Pattern.compile(
                RECORD_PATTERN
        );
        Matcher recordMatcher = recordPattern.matcher(logEntry);
        LogRecord logRecord = null;
        if (recordMatcher.find()) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
            LocalDateTime localDateTime = LocalDateTime.parse(recordMatcher.group(GROUP_DATE_TIME), dateTimeFormatter);
            logRecord = new LogRecord(
                    recordMatcher.group(GROUP_REMOTE_ADDR),
                    recordMatcher.group(GROUP_REMOTE_USER),
                    localDateTime,
                    recordMatcher.group(GROUP_REQUEST),
                    Integer.parseInt(recordMatcher.group(GROUP_STATUS)),
                    Integer.parseInt(recordMatcher.group(GROUP_BODY_BYTES_SENT)),
                    recordMatcher.group(GROUP_HTTP_REFER),
                    recordMatcher.group(GROUP_HTTP_USER_AGENT),
                    recordMatcher.group(GROUP_REQUEST_TYPE)
            );
        }
        return logRecord;
    }
}
