package edu.hw3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Task3 {
    private Task3() {
    }

    public static Map<Object, Integer> freqDict(Object[] objects) {
        Objects.requireNonNull(objects);
        Map<Object, Integer> mapFrequency = new HashMap<>();
        for (Object object : objects) {
            if (mapFrequency.containsKey(object)) {
                mapFrequency.put(object, mapFrequency.get(object) + 1);
            } else {
                mapFrequency.put(object, 1);
            }
        }
        return mapFrequency;
    }
}
