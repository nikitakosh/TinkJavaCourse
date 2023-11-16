package edu.hw6;

import edu.hw6.task4.Task4;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CompositeOutputStreamTest {

    @Test
    @DisplayName("composite output stream")
    public void compositeOutputStream() {
        Task4.compositeOutputStream();
        byte[] bytes;
        Path path = Path.of("src/main/java/edu/hw6/task4/", "file.txt");
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String actualText = "Programming is learned by writing programs. - Brian Kernighan";
        StringBuilder textFromFile = new StringBuilder();
        for (byte aByte : bytes) {
            textFromFile.append((char) aByte);
        }
        Assertions.assertEquals(textFromFile.toString().strip(), actualText);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
