package edu.hw6.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task2 {

    public static final String REGEX_DELIMITER = "\\.";

    private Task2() {}

    public static void cloneFile(Path path) {
        Path currPath = path;
        int count = 0;
        String baseName =  currPath.getFileName().toString().split(REGEX_DELIMITER)[0];
        String extension = "";
        if (currPath.getFileName().toString().split(REGEX_DELIMITER).length > 1) {
            extension = "." + currPath.getFileName().toString().split(REGEX_DELIMITER)[1];
        }
        while (Files.exists(currPath)) {
            if (count == 0) {
                currPath = Paths.get(currPath.toFile().getParent(), baseName + " - копия" + extension);
            } else {
                currPath = Paths.get(
                        currPath.toFile().getParent(), baseName + String.format(" - копия (%d)", count) + extension
                );
            }
            count++;
        }
        try {
            Files.createFile(currPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
