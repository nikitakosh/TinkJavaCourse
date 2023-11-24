package edu.project3.handlers;

import edu.project3.parsers.LogFileParser;
import edu.project3.parsers.LogHTTPParser;
import edu.project3.parsers.LogParser;

public class PathHandler {
    public LogParser handlePath(String path) {
        if (isHttpPath(path)) {
            return new LogHTTPParser(path);
        } else {
            return new LogFileParser(path);
        }
    }

    public boolean isHttpPath(String path) {
        return path.startsWith("http://") || path.startsWith("https://");
    }

}
