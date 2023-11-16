package edu.hw6.task4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import org.assertj.core.util.Files;

public class Task4 {

    public static final String TEXT = "Programming is learned by writing programs. - Brian Kernighan";

    private Task4() {
    }

    public static void compositeOutputStream() {
        File file = Files.newFile("src/main/java/edu/hw6/task4/" + "file.txt");
        try (OutputStream outputStream = new FileOutputStream(file);
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
