package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MultiThreadMonteCarlo {

    public static final int CIRCLE_AREA = 4;

    public double calculatePi(int countIteration, int countThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(countThreads);
        Callable<Integer> task = () -> {
            int circleCount = 0;
            for (int i = 0; i < countIteration / countThreads; i++) {
                double x = ThreadLocalRandom.current().nextDouble(2);
                double y = ThreadLocalRandom.current().nextDouble(2);
                if (isInCircle(x, y)) {
                    circleCount++;
                }
            }
            return circleCount;
        };
        List<Callable<Integer>> tasks = new ArrayList<>();
        IntStream.range(0, countThreads).forEach((i) -> tasks.add(task));
        List<Future<Integer>> futures;
        try {
            futures = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executor.shutdown();
        int circleCount = futures.stream().mapToInt(future -> {
            try {
                return future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).sum();

        return CIRCLE_AREA * ((double) circleCount / countIteration);
    }


    public boolean isInCircle(double x, double y) {
        return Math.pow(x - 1, 2) + Math.pow(y - 1, 2) <= 1;
    }
}
