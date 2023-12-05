package edu.hw9.task2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class FindFilesWithExtension extends RecursiveTask<List<Path>> {
    private final Path path;
    private final String fileExtension;

    public FindFilesWithExtension(Path path, String fileExtension) {
        this.path = path;
        this.fileExtension = fileExtension;
    }

    @Override
    protected List<Path> compute() {
        if (path.toFile().isFile()) {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + "*." + fileExtension);
            return pathMatcher.matches(path.getFileName()) ? List.of(path) : List.of();
        }
        List<FindFilesWithExtension> fileRecursiveTasks = new ArrayList<>();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(path)) {
            paths.forEach(path -> fileRecursiveTasks.add(new FindFilesWithExtension(path, fileExtension)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileRecursiveTasks.forEach(ForkJoinTask::fork);
        return fileRecursiveTasks.stream().map(ForkJoinTask::join).flatMap(Collection::stream).toList();
    }

}
