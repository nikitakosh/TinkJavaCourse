package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Task1 {

    public static final int MYSELF_COUNT_THREADS = 4;
    public static final int INCREASE_VALUE = 1000;
    public static final int TIMEOUT = 800;

    private Task1() {
    }

    public static int increaseNumberBy4000(int startValue) {
        AtomicInteger atomicInteger = new AtomicInteger(startValue);
        ExecutorService executorService = Executors.newFixedThreadPool(MYSELF_COUNT_THREADS);
        Runnable increase = () -> IntStream.range(0, INCREASE_VALUE).forEach(i -> atomicInteger.incrementAndGet());
        IntStream.range(0, MYSELF_COUNT_THREADS).forEach(i -> executorService.execute(increase));
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(TIMEOUT, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
        return atomicInteger.get();
    }
}
