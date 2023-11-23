package edu.hw6.task1;

import edu.hw6.task1.exception.DuplicateKeyException;
import edu.hw6.task1.exception.GetEmptyMapException;
import edu.hw6.task1.exception.RemoveNonexistentKeyException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;



public class DiskMap implements Map<String, String> {
    private static final String START_FILE_NAME = "data";
    private static final String FILE_EXTENSION = ".txt";
    private static final int BUFFER_CAPACITY = 1024;
    private static final String ABSOLUTE_PATH =
            "src/main/java/edu/hw6/task1";
    private Path path;
    private int size;

    public DiskMap() {
        path = Paths.get(ABSOLUTE_PATH, START_FILE_NAME + new Random().nextInt() + FILE_EXTENSION);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return !isEmpty() && contains(key, true);
    }

    @Override
    public boolean containsValue(Object value) {
        return !isEmpty() && contains(value, false);
    }

    @Override
    public String get(Object key) {
        if (isEmpty()) {
            throw new GetEmptyMapException("get empty map");
        }
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesRead = channel.read(buffer);
            StringBuilder currKey = new StringBuilder();
            StringBuilder currValue = new StringBuilder();
            boolean isNowKey = true;
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currChar = (char) buffer.get();
                    if (currChar == ' ') {
                        if (currKey.toString().equals(key)) {
                            return currValue.toString();
                        }
                        currKey = new StringBuilder();
                        currValue = new StringBuilder();
                        isNowKey = true;
                    } else if (currChar == ':') {
                        isNowKey = false;
                    } else if (isNowKey) {
                        currKey.append(currChar);
                    } else {
                        currValue.append(currChar);
                    }
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        if (containsKey(key)) {
            throw new DuplicateKeyException("duplicate key");
        }
        String entry = key + ":" + value + " ";
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            ByteBuffer buffer = ByteBuffer.allocate(entry.getBytes().length);
            buffer.put(entry.getBytes());
            buffer.flip();
            channel.write(buffer);
            size++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return entry;
    }

    @Override
    public String remove(Object key) {
        if (!containsKey(key)) {
            throw new RemoveNonexistentKeyException("remove non-existent key exception");
        }
        String deletedValue = get(key);
        Path newPath = Paths.get(ABSOLUTE_PATH, START_FILE_NAME + new Random().nextInt() + FILE_EXTENSION);
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.DELETE_ON_CLOSE);
             FileChannel newChannel = FileChannel.open(newPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesRead = channel.read(buffer);
            StringBuilder currKey = new StringBuilder();
            StringBuilder currValue = new StringBuilder();
            boolean isNowKey = true;
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currChar = (char) buffer.get();
                    if (currChar == ' ') {
                        if (!currKey.toString().equals(key)) {
                            String entry = currKey + ":" + currValue + " ";
                            ByteBuffer bufferForOneRecord = ByteBuffer.allocate(entry.getBytes().length);
                            bufferForOneRecord.put(entry.getBytes());
                            bufferForOneRecord.flip();
                            newChannel.write(bufferForOneRecord);
                        }
                        currKey = new StringBuilder();
                        currValue = new StringBuilder();
                        isNowKey = true;
                    } else if (currChar == ':') {
                        isNowKey = false;
                    } else if (isNowKey) {
                        currKey.append(currChar);
                    } else {
                        currValue.append(currChar);
                    }
                }
                buffer.clear();
                bytesRead = channel.read(buffer);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path = newPath;
        size--;
        return deletedValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (Entry<? extends String, ? extends String> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        path = Paths.get(ABSOLUTE_PATH, START_FILE_NAME + new Random().nextInt() + FILE_EXTENSION);
        size = 0;
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();
        readFileAndAddElements(keys, true);
        return keys;
    }

    @NotNull
    @Override
    public Collection<String> values() {
        List<String> values = new ArrayList<>();
        readFileAndAddElements(values, false);
        return values;
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> entrySet = new HashSet<>();
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesRead = channel.read(buffer);
            StringBuilder currKey = new StringBuilder();
            StringBuilder currValue = new StringBuilder();
            boolean isNowKey = true;
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currChar = (char) buffer.get();
                    if (currChar == ' ') {
                        entrySet.add(Map.entry(currKey.toString(), currValue.toString()));
                        currKey = new StringBuilder();
                        currValue = new StringBuilder();
                        isNowKey = true;
                    } else if (currChar == ':') {
                        isNowKey = false;
                    } else if (isNowKey) {
                        currKey.append(currChar);
                    } else {
                        currValue.append(currChar);
                    }
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entrySet;
    }

    private boolean contains(Object arg, boolean isKey) {
        boolean isNowKey = isKey;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesRead = channel.read(buffer);
            StringBuilder currArg = new StringBuilder();
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currChar = (char) buffer.get();
                    if (currChar == ' ') {
                        if (currArg.toString().equals(arg)) {
                            return true;
                        }
                        currArg = new StringBuilder();
                        isNowKey = !isNowKey;
                    } else if (currChar == ':') {
                        isNowKey = !isNowKey;
                    } else if (isNowKey) {
                        currArg.append(currChar);
                    }
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private void readFileAndAddElements(Collection<String> collection, boolean isKey) {
        boolean isNowKey = isKey;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
            int bytesRead = channel.read(buffer);
            StringBuilder currElement = new StringBuilder();
            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    char currChar = (char) buffer.get();
                    if (currChar == ' ') {
                        collection.add(currElement.toString());
                        currElement = new StringBuilder();
                        isNowKey = !isNowKey;
                    } else if (currChar == ':') {
                        isNowKey = !isNowKey;
                    } else if (isNowKey) {
                        currElement.append(currChar);
                    }
                }
                buffer.clear();
                bytesRead = channel.read(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
