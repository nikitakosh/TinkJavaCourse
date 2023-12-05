package edu.hw9.task1;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    private final Map<String, List<Double>> stats;
    private final ExecutorService executorService;
    private final List<Map.Entry<String, Future<Double>>> futures;
    public StatsCollector() {
        this.stats = new HashMap<>();
        this.executorService = Executors.newFixedThreadPool(4);
        this.futures = Collections.synchronizedList(new ArrayList<>());
    }

    public void push(String metricName, Double[] data) {
        switch (metricName) {
            case "sum" -> futures.add(
                    new AbstractMap.SimpleEntry<>("sum", executorService.submit(new CalculateSum(data)))
            );
            case "min" -> futures.add(
                    new AbstractMap.SimpleEntry<>("min", executorService.submit(new CalculateMinimum(data)))
            );
            case "max" -> futures.add(
                    new AbstractMap.SimpleEntry<>("max", executorService.submit(new CalculateMaximum(data)))
            );
            case "avg" -> futures.add(
                    new AbstractMap.SimpleEntry<>("avg", executorService.submit(new CalculateAverage(data)))
            );
            default -> throw new InvalidMetricNameException("invalid metric name");
        }
    }

    public Set<Map.Entry<String, List<Double>>> stats() {
        for(Map.Entry<String, Future<Double>> future : futures){
                try {
                    String metricName = future.getKey();
                    Double metricValue = future.getValue().get();
                    if (stats.containsKey(metricName)) {
                        stats.get(metricName).add(metricValue);
                    } else {
                        stats.put(metricName, new ArrayList<>(List.of(metricValue)));
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        return stats.entrySet();
    }
}
