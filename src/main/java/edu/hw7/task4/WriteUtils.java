package edu.hw7.task4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteUtils {

    private WriteUtils() {}

    public static void writeInMarkdown(String info) {
        Path path = Path.of(
                "src/main/resources/output", "output.md");
        try {
            Files.write(path, info.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
