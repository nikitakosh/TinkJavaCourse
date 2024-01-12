package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FindFilesBySize extends RecursiveTask<List<Path>> {
    private final Path path;
    private final long size;

    public FindFilesBySize(Path path, long size) {
        this.path = path;
        this.size = size;
    }

    @Override
    protected List<Path> compute() {
        if (path.toFile().isFile()) {
            return path.toFile().length() > size ? new ArrayList<>(List.of(path)) : new ArrayList<>();
        }
        List<FindFilesBySize> findFilesBySizeRecursiveTasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
            paths.forEach(path -> findFilesBySizeRecursiveTasks.add(new FindFilesBySize(path, size)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        findFilesBySizeRecursiveTasks.forEach(ForkJoinTask::fork);
        return new ArrayList<>(findFilesBySizeRecursiveTasks.stream()
                .map(ForkJoinTask::join).flatMap(Collection::stream).toList());
    }
}
