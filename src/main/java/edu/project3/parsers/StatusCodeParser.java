package edu.project3.parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StatusCodeParser {

    public Map<Integer, String> parseStatusCode(String path) {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines.map(line -> line.split(" ", 2)).collect(Collectors.toMap(
                    codeInfo -> Integer.parseInt(codeInfo[0]),
                    codeInfo -> codeInfo[1]
            ));
        } catch (IOException e) {
            throw new RuntimeException("can't read csv file");
        }
    }
}
