package edu.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FibonacciCalculator {


    public static final int THREADS = 10;

    public List<Long> calculate(int countFibonacciNumbers) {
        List<Long> fibonacciNumbers = new ArrayList<>();
        try (FixedThreadPool fixedThreadPool = FixedThreadPool.create(THREADS)) {
            fixedThreadPool.start();
            IntStream.range(1, countFibonacciNumbers + 1).forEach(
                    i -> fixedThreadPool.execute(() -> fibonacciNumbers.add(fibonacci(i)))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fibonacciNumbers;
    }

    public long fibonacci(int n) {
        return n <= 2 ? 1 : fibonacci(n - 1) + fibonacci(n - 2);
    }
}
