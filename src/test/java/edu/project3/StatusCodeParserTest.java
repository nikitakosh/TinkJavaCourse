package edu.project3;

import edu.project3.parsers.StatusCodeParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StatusCodeParserTest {

    @Test
    @DisplayName("parse status code from csv")
    public void parse() {
        StatusCodeParser statusCodeParser = new StatusCodeParser();
        Map<Integer, String> statusCodes = statusCodeParser.parseStatusCode("src/main/resources/project3/statusCodes.txt");
        Assertions.assertEquals(statusCodes.size(), 63);
    }
}
