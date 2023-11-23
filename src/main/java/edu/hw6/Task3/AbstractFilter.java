package edu.hw6.Task3;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    AbstractFilter IS_WRITABLE_FILE = path -> path.toFile().canWrite();
    AbstractFilter IS_READABLE_FILE = path -> path.toFile().canRead();

    static AbstractFilter largerThan(long size) {
        return path -> path.toFile().length() > size;
    }

    static AbstractFilter globMatches(String glob) {
        return path -> {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + glob);
            return pathMatcher.matches(path.getFileName());
        };
    }

    static AbstractFilter containsRegex(String regex) {
        return path -> path.toFile().getName().matches(regex);
    }

    static AbstractFilter magicNumber(int... magicBytes) {
        return path -> {
            if (magicBytes.length == 0) {
                return true;
            }
            byte[] actualBytes = Files.readAllBytes(path);
            for (int i = 0; i < magicBytes.length; i++) {
                if ((byte) magicBytes[i] != actualBytes[i]) {
                    return false;
                }
            }
            return true;
        };
    }

    @Override
    boolean accept(Path path) throws IOException;

    default AbstractFilter and(AbstractFilter other) {
        return (path) -> this.accept(path) && other.accept(path);
    }

}
