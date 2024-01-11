package edu.project3;

import edu.project3.handlers.PathHandler;
import edu.project3.parsers.LogFileParser;
import edu.project3.parsers.LogHTTPParser;
import edu.project3.parsers.LogParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PathHandlerTest {
    @Test
    @DisplayName("handle path")
    public void handlePath() {
        PathHandler pathHandler = new PathHandler();
        LogParser logParser = pathHandler.handlePath("logs/2023*");
        Assertions.assertInstanceOf(LogFileParser.class, logParser);
        logParser = pathHandler.handlePath("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        Assertions.assertInstanceOf(LogHTTPParser.class, logParser);
    }

    @Test
    @DisplayName("is http path")
    public void isHttpPath() {
        PathHandler pathHandler = new PathHandler();
        Assertions.assertTrue(pathHandler.isHttpPath("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"));
        Assertions.assertFalse(pathHandler.isHttpPath("logs/2023*"));
    }
}
