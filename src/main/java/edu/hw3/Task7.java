package edu.hw3;

import java.util.TreeMap;

public class Task7 {

    private Task7() {
    }

    public static TreeMap<String, String> addNullToTreeMap() {
        TreeMap<String, String> treeMap = new TreeMap<>((s1, s2) -> {
            if (s1 == null && s2 == null) {
                return 0;
            } else if (s1 == null) {
                return -1;
            } else if (s2 == null) {
                return 1;
            }
            return s1.compareTo(s2);
        });
        treeMap.put(null, "null");
        return treeMap;
    }
}
