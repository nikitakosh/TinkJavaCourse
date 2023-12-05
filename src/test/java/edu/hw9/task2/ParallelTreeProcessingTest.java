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
        List<Path> directories = parallelTreeProcessing.findDirectory(3);
        directories.sort(Path::compareTo);
        Assertions.assertEquals(directories,
                List.of(
                        Path.of("src/main/resources/hw9Task2"),
                        Path.of("src/main/resources/hw9Task2/dir1"),
                        Path.of("src/main/resources/hw9Task2/dir2")
                ));
        directories = parallelTreeProcessing.findDirectory(4);
        directories.sort(Path::compareTo);
        Assertions.assertEquals(directories,
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir2")
                ));
    }

    @Test
    @DisplayName("find files with extension")
    public void findFilesWithExtension() {
        List<Path> files = parallelTreeProcessing.findFilesWithExtension("txt");
        files.sort(Path::compareTo);
        Assertions.assertEquals(files,
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir1/file4.txt"),
                        Path.of("src/main/resources/hw9Task2/dir2/file2.txt"),
                        Path.of("src/main/resources/hw9Task2/dir2/file3.txt"),
                        Path.of("src/main/resources/hw9Task2/file1.txt")
                ));
        files = parallelTreeProcessing.findFilesWithExtension("png");
        files.sort(Path::compareTo);
        Assertions.assertEquals(files,
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
        List<Path> files = parallelTreeProcessing.findFilesBySize(200000);
        files.sort(Path::compareTo);
        Assertions.assertEquals(files,
                List.of(
                    Path.of("src/main/resources/hw9Task2/dir2/img5.jpg")
                ));
        files = parallelTreeProcessing.findFilesBySize(100000);
        files.sort(Path::compareTo);
        Assertions.assertEquals(files,
                List.of(
                        Path.of("src/main/resources/hw9Task2/dir2/img5.jpg"),
                        Path.of("src/main/resources/hw9Task2/dir2/img6.jpg")
                ));

    }
}
