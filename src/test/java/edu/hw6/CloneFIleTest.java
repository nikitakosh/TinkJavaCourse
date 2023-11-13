package edu.hw6;

import edu.hw6.task2.Task2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

public class CloneFIleTest {

    @Test
    @DisplayName("clone file")
    public void cloneFile() {
        String directory = "C:\\Projects\\TinkJavaCourse\\src\\main\\java\\edu\\hw6\\task2";
        String filename = "Tinkoff Bank Biggest Secret.txt";
        Path path = Path.of(directory, filename);
        Task2.cloneFile(path);
        Assertions.assertTrue(Files.exists(path));
        Task2.cloneFile(path);
        Assertions.assertTrue(Files.exists(Path.of(directory, filename.split("\\.")[0] + " - копия." + filename.split("\\.")[1])));
        Task2.cloneFile(path);
        Assertions.assertTrue(Files.exists(Path.of(directory, filename.split("\\.")[0] + " - копия (1)." + filename.split("\\.")[1])));
        Task2.cloneFile(path);
        Assertions.assertTrue(Files.exists(Path.of(directory, filename.split("\\.")[0] + " - копия (2)." + filename.split("\\.")[1])));
    }

}
