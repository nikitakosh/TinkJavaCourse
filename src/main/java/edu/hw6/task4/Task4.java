package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

public class Task4 {

    public static final String TEXT = "Programming is learned by writing programs. - Brian Kernighan";

    private Task4() {
    }

    public static void compositeOutputStream() {
        Path path;
        try {
            path = Files.createFile(Path.of("src/main/java/edu/hw6/task4/", "file.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (OutputStream outputStream = new FileOutputStream(path.toFile());
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)
        ) {
            printWriter.println(TEXT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
