package edu.hw9;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    private final Map<String, Double> stats;
    private final ExecutorService executorService;
    private final List<Future<Double>> futures;
    public StatsCollector() {
        this.stats = new HashMap<>();
        this.executorService = Executors.newFixedThreadPool(4);
        this.futures = new ArrayList<>();
    }


    public void push(String metricName, Double[] data) {
        switch (metricName) {
            case "sum" -> futures.add(executorService.submit(new CalculateSum(data)));
            case "min" -> futures.add(executorService.submit(new CalculateMinimum(data)));
            case "max" -> futures.add(executorService.submit(new CalculateMaximum(data)));
            case "avg" -> futures.add(executorService.submit(new CalculateAverage(data)));
        }
    }

//    public Set<Map.Entry<String, Double>> stats() {
//        for(Future<Double> future : futures){
//            try {
//                stats.put();
//            } catch (InterruptedException | ExecutionException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return stats.entrySet();
//    }
}
