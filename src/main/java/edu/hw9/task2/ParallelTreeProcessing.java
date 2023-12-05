package edu.hw9.task2;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ParallelTreeProcessing {

    public List<Path> findFilesWithExtension(String fileExtension) {
        FindFilesWithExtension fileRecursiveTask =
                new FindFilesWithExtension(Path.of("src/main/resources/hw9Task2"), fileExtension);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(fileRecursiveTask);
        }
    }

    public List<Path> findFilesBySize(long size) {
        FindFilesBySize fileRecursiveTask =
                new FindFilesBySize(Path.of("src/main/resources/hw9Task2"), size);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(fileRecursiveTask);
        }
    }

    public List<Path> findDirectory(int countFiles) {
        FindDirectory findDirectoryRecursiveTask =
                new FindDirectory(Path.of("src/main/resources/hw9Task2"), countFiles);
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(findDirectoryRecursiveTask);
        }
    }

}
