package edu.hw9.task1;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StatsCollector {
    public static final String SUM = "sum";
    public static final String MIN = "min";
    public static final String MAX = "max";
    public static final String AVG = "avg";
    public static final int THREADS = 4;
    private final Map<String, List<Double>> stats;
    private final ExecutorService executorService;
    private final List<Map.Entry<String, Future<Double>>> futures;

    public StatsCollector() {
        this.stats = new HashMap<>();
        this.executorService = Executors.newFixedThreadPool(THREADS);
        this.futures = Collections.synchronizedList(new ArrayList<>());
    }

    public void push(String metricName, Double[] data) {
        switch (metricName) {
            case SUM -> futures.add(
                    new AbstractMap.SimpleEntry<>(SUM, executorService.submit(new CalculateSum(data)))
            );
            case MIN -> futures.add(
                    new AbstractMap.SimpleEntry<>(MIN, executorService.submit(new CalculateMinimum(data)))
            );
            case MAX -> futures.add(
                    new AbstractMap.SimpleEntry<>(MAX, executorService.submit(new CalculateMaximum(data)))
            );
            case AVG -> futures.add(
                    new AbstractMap.SimpleEntry<>(AVG, executorService.submit(new CalculateAverage(data)))
            );
            default -> throw new InvalidMetricNameException("invalid metric name");
        }
    }

    public Set<Map.Entry<String, List<Double>>> stats() {
        for (Map.Entry<String, Future<Double>> future : futures) {
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
