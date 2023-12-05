package edu.hw9.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

public class ParallelTreeProcessingTest {

    private ParallelTreeProcessing parallelTreeProcessing;

    @BeforeEach
    public void setUp() {
        parallelTreeProcessing = new ParallelTreeProcessing();
    }

    @Test
    @DisplayName("find directory with more than countFiles")
    public void findDirectory() {
        Assertions.assertEquals(parallelTreeProcessing.findDirectory(3),
                List.of(
                        Path.of("src/main/resources/hw9Task2"),
                        Path.of("src/main/resources/hw9Task2/dir1"),
                        Path.of("src/main/resources/hw9Task2/dir2")
                ));
        Assertions.assertEquals(parallelTreeProcessing.findDirectory(4),
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir2")
                ));
    }

    @Test
    @DisplayName("find files with extension")
    public void findFilesWithExtension() {
        Assertions.assertEquals(parallelTreeProcessing.findFilesWithExtension("txt"),
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir1/file4.txt"),
                        Path.of("src/main/resources/hw9Task2/dir2/file2.txt"),
                        Path.of("src/main/resources/hw9Task2/dir2/file3.txt"),
                        Path.of("src/main/resources/hw9Task2/file1.txt")
                ));
        Assertions.assertEquals(parallelTreeProcessing.findFilesWithExtension("png"),
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir1/img2.png"),
                        Path.of("src/main/resources/hw9Task2/dir1/img3.png"),
                        Path.of("src/main/resources/hw9Task2/dir2/img4.png"),
                        Path.of("src/main/resources/hw9Task2/img1.png")
                ));
    }

    @Test
    @DisplayName("find files by size")
    public void findFilesBySize() {
        Assertions.assertEquals(parallelTreeProcessing.findFilesBySize(200000),
                List.of(
                    Path.of("src/main/resources/hw9Task2/dir2/img5.jpg")
                ));
        Assertions.assertEquals(parallelTreeProcessing.findFilesBySize(100000),
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir2/img5.jpg"),
                        Path.of("src/main/resources/hw9Task2/dir2/img6.jpg")
                ));

    }
}
