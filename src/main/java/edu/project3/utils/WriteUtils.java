package edu.project3.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteUtils {

    public static final String PATH_TO_OUTPUT = "src/main/java/edu/project3/output";

    private WriteUtils() {}

    public static void writeInMarkdown(String logInfo) {
        Path path = Path.of(
                PATH_TO_OUTPUT, "output.md");
        try {
            Files.write(path, logInfo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeInAdoc(String logInfo) {
        Path path = Path.of(PATH_TO_OUTPUT, "output.adoc");
        try {
            Files.write(path, logInfo.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
