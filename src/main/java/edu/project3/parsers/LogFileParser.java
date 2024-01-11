package edu.project3.parsers;

import edu.project3.LogRecord;
import edu.project3.utils.LogRecordParserUtils;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LogFileParser implements LogParser {
    private final String path;

    public LogFileParser(String path) {
        this.path = path;
    }


    public List<Path> parseLogFiles() {
        List<Path> paths = new ArrayList<>();
        try (Stream<Path> pathStream = Files.walk(Path.of("src/main/resources/project3/logs"))) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + path + "*");
            pathStream.filter(path -> pathMatcher.matches(path) && !path.toFile().isDirectory()).forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException("input invalid path");
        }
        return paths;
    }

    @Override
    public List<LogRecord> parseLogRecord() {
        List<Path> paths = parseLogFiles();
        List<LogRecord> logRecords = new ArrayList<>();
        for (Path logPath : paths) {
            try (Stream<String> records = Files.lines(logPath)) {
                logRecords.addAll(records.map(LogRecordParserUtils::parse).toList());
            } catch (IOException e) {
                throw new RuntimeException("can't read file with logs");
            }
        }
        return logRecords;
    }
}
