package edu.project3.parsers;

import edu.project3.LogRecord;
import edu.project3.utils.LogRecordParserUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

public class LogHTTPParser implements LogParser {

    private final String path;

    public LogHTTPParser(String path) {
        this.path = path;
    }

    private String parseData() {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(this.path))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException("input invalid URI");
        }
        HttpResponse<String> response;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("no response received");
        }
        return response.body();
    }

    @Override
    public List<LogRecord> parseLogRecord() {
        String data = parseData();
        return Arrays.stream(data.split("\n"))
                .map(LogRecordParserUtils::parse)
                .toList();
    }

}
