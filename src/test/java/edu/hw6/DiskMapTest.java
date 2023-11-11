package edu.hw6;

import edu.hw6.task1.DiskMap;
import edu.hw6.task1.exception.DuplicateKeyException;
import edu.hw6.task1.exception.GetEmptyMapException;
import edu.hw6.task1.exception.RemoveNonexistentKeyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DiskMapTest {


    @Test
    @DisplayName("MapDisk put and get")
    public void putAndGet() {
        DiskMap diskMap = new DiskMap();
        Assertions.assertThrows(GetEmptyMapException.class, () -> diskMap.get("key1"));
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertEquals(diskMap.get("key2"), "val2");
        Assertions.assertThrows(DuplicateKeyException.class, () -> diskMap.put("key1", "val3"));
    }

    @Test
    @DisplayName("MapDisk remove entity")
    public void removeEntity() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertEquals(diskMap.remove("key1"), "val1");
        Assertions.assertNull(diskMap.get("key1"));
        Assertions.assertThrows(RemoveNonexistentKeyException.class, () -> diskMap.remove("key1"));
    }

    @Test
    @DisplayName("MapDisk putAll")
    public void putAll() {
        DiskMap diskMap1 = new DiskMap();
        DiskMap diskMap2 = new DiskMap();
        diskMap1.put("key1", "val1");
        diskMap1.put("key2", "val2");
        diskMap2.putAll(diskMap1);
        Assertions.assertEquals(diskMap2.get("key1"), "val1");
        Assertions.assertEquals(diskMap2.get("key2"), "val2");
    }

    @Test
    @DisplayName("MapDisk entrySet")
    public void entrySet() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertEquals(diskMap.entrySet(), Set.of(Map.entry("key1", "val1"), Map.entry("key2", "val2")));
    }

    @Test
    @DisplayName("DiskMap keySet")
    public void keySet() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertEquals(diskMap.keySet(), Set.of("key1", "key2"));
    }

    @Test
    @DisplayName("DiskMap values")
    public void values() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertEquals(diskMap.values(), List.of("val1", "val2"));
    }

    @Test
    @DisplayName("DiskMap clear")
    public void clear() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        diskMap.clear();
        Assertions.assertThrows(GetEmptyMapException.class, () -> diskMap.get("key1"));
        Assertions.assertTrue(diskMap.isEmpty());
    }

    @Test
    @DisplayName("DiskMap containsKey")
    public void containsKey() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertTrue(diskMap.containsKey("key2"));
        Assertions.assertFalse(diskMap.containsKey("key3"));
    }

    @Test
    @DisplayName("DiskMap containsValue")
    public void containsValue() {
        DiskMap diskMap = new DiskMap();
        diskMap.put("key1", "val1");
        diskMap.put("key2", "val2");
        Assertions.assertTrue(diskMap.containsValue("val1"));
        Assertions.assertFalse(diskMap.containsValue("val3"));
    }

    @Test
    @DisplayName("DiskMap isEmpty")
    public void isEmpty() {
        DiskMap diskMap = new DiskMap();
        Assertions.assertTrue(diskMap.isEmpty());
        diskMap.put("key1", "val1");
        Assertions.assertFalse(diskMap.isEmpty());
        diskMap.remove("key1");
        Assertions.assertTrue(diskMap.isEmpty());
    }
    @Test
    @DisplayName("DiskMap size")
    public void size() {
        DiskMap diskMap = new DiskMap();
        Assertions.assertEquals(diskMap.size(), 0);
        diskMap.put("key1", "val1");
        Assertions.assertEquals(diskMap.size(), 1);
        diskMap.remove("key1");
        Assertions.assertEquals(diskMap.size(), 0)  ;
    }
}
