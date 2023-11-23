package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Task1 {

    private Task1() {
    }

    public static int increaseNumber(int increaseValue) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Thread incrementor1 = new Thread(() -> IntStream.range(0, increaseValue / 2).forEach(
                (num) -> atomicInteger.incrementAndGet())
        );
        Thread incrementor2 = new Thread(() -> IntStream.range(increaseValue / 2, increaseValue).forEach(
                (num) -> atomicInteger.incrementAndGet())
        );
        Thread incrementor3 = new Thread(() -> IntStream.range(increaseValue / 2, increaseValue).forEach(
                (num) -> atomicInteger.incrementAndGet())
        );
        incrementor1.start();
        incrementor2.start();
        incrementor3.start();
        try {
            incrementor1.join();
            incrementor2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return atomicInteger.get();
    }
}
