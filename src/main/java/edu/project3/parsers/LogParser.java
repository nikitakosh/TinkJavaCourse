package edu.project3.parsers;

import edu.project3.LogRecord;
import java.util.List;

public interface LogParser {
    List<LogRecord> parseLogRecord();
}
