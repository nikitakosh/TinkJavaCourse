package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Stream;

public class FindDirectory extends RecursiveTask<List<Path>> {

    private final Path path;
    private final int countFiles;


    public FindDirectory(Path path, int countFiles) {
        this.path = path;
        this.countFiles = countFiles;
    }

    @Override
    protected List<Path> compute() {
        List<FindDirectory> recursiveTasks =
                new ArrayList<>();
        List<Path> pathsWithManyFiles = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(path, 1)) {
            List<Path> listPaths = paths.skip(1).toList();
            listPaths.forEach(path -> {
                if (path.toFile().isDirectory()) {
                    FindDirectory recursiveTask =
                            new FindDirectory(path, countFiles);
                    recursiveTask.fork();
                    recursiveTasks.add(recursiveTask);
                }
            });
            if (listPaths.size() > countFiles) {
                pathsWithManyFiles.add(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var findDirectoryRecursiveTask : recursiveTasks) {
            pathsWithManyFiles.addAll(findDirectoryRecursiveTask.join());
        }
        return pathsWithManyFiles;
    }

}
