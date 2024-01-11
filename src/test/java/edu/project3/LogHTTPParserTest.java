package edu.project3;

import edu.project3.parsers.LogFileParser;
import edu.project3.parsers.LogHTTPParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogHTTPParserTest {
    @Test
    @DisplayName("parse LogRecord")
    public void parseLogRecord() {
        LogHTTPParser logHTTPParser = new LogHTTPParser("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        Assertions.assertEquals(logHTTPParser.parseLogRecord(), new LogFileParser("logs/2024*").parseLogRecord());
    }
}
