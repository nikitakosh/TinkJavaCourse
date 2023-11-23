package edu.hw6;

import edu.hw6.Task3.AbstractFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

public class AbstractFilterTest {

    @Test
    @DisplayName("larger than")
    public void largerThan() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.largerThan(100000))) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/bigImage.png")
        ));
    }

    @Test
    @DisplayName("is file readable")
    public void isReadable() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.IS_READABLE_FILE)) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/bigImage.png"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloJavaWorld.txt"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloKotlinWorld.txt"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/smallImage.png")
        ));
    }

    @Test
    @DisplayName("is file writable")
    public void isWritable() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.IS_WRITABLE_FILE)) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/bigImage.png"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloJavaWorld.txt"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloKotlinWorld.txt"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/smallImage.png")
        ));
    }

    @Test
    @DisplayName("glob matches")
    public void globMatches() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.globMatches("*.png"))) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/bigImage.png"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/smallImage.png")
        ));
    }

    @Test
    @DisplayName("contains regex")
    public void containsRegex() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.containsRegex("^file.*"))) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloJavaWorld.txt"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/fileHelloKotlinWorld.txt")
        ));
    }

    @Test
    @DisplayName("contains magic numbers")
    public void magicNumbers() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.magicNumber(0x89, 'P', 'N', 'G'))) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/bigImage.png"),
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/smallImage.png")
        ));
    }

    @Test
    @DisplayName("multiple filter")
    public void and() {
        Set<Path> paths = new HashSet<>();
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("src/main/java/edu/hw6/Task3/filesForTest/"), AbstractFilter.globMatches("*.png").and(AbstractFilter.containsRegex("^small.*")))) {
            entries.forEach(paths::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(paths, Set.of(
                Path.of("src/main/java/edu/hw6/Task3/filesForTest/smallImage.png")
        ));
    }
}
