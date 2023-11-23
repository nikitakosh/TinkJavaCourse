package edu.hw7;

import java.util.stream.LongStream;

public class Task2 {

    private Task2() {
    }

    public static Long factorial(int num) {
        return LongStream.rangeClosed(2, num).parallel().reduce(1L, (x, y) -> x * y);
    }

}
