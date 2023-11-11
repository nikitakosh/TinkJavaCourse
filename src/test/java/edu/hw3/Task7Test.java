package edu.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class Task7Test {

    @Test
    @DisplayName("add null to TreeMap")
    public void addNullToTreeMap() {
        TreeMap<String, String> treeMap = Task7.addNullToTreeMap();
        Assertions.assertTrue(treeMap.containsKey(null));
    }
}
