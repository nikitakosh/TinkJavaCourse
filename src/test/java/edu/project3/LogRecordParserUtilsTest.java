package edu.project3;

import edu.project3.utils.LogRecordParserUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LogRecordParserUtilsTest {

    @Test
    @DisplayName("parse log record")
    public void parseLogRecord() {
        String record = "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"";
        Assertions.assertEquals(LogRecordParserUtils.parse(record),
                new LogRecord("93.180.71.3", "-", LocalDateTime.of(2015, 5, 17, 8, 5, 32),
                        "/downloads/product_1", 304, 0, "-", "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)", "GET"));
    }
}
