package edu.hw9.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollectorTest {

    @Test
    @DisplayName("single thread push data in stats collector")
    public void singleThreadPushData() {
        StatsCollector statsCollector = new StatsCollector();
        statsCollector.push("sum", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        statsCollector.push("max", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        statsCollector.push("min", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        statsCollector.push("avg", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
        Assertions.assertThrows(InvalidMetricNameException.class,
                () -> statsCollector.push("exp", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3}));
        for (var metric : statsCollector.stats()) {
            switch (metric.getKey()) {
                case "sum" -> Assertions.assertEquals(metric.getValue(), List.of(6.949999999999999));
                case "max" -> Assertions.assertEquals(metric.getValue(), List.of(5.1));
                case "avg" -> Assertions.assertEquals(metric.getValue(), List.of(1.39));
                case "min" -> Assertions.assertEquals(metric.getValue(), List.of(0.05));
            }
        }
    }

    @Test
    @DisplayName("multi thread push data in stats")
    public void multiThreadPushData() {
        StatsCollector statsCollector = new StatsCollector();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for(int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                statsCollector.push("sum", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
                statsCollector.push("max", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
                statsCollector.push("min", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
                statsCollector.push("avg", new Double[] {0.1, 0.05, 1.4, 5.1, 0.3});
            });
        }
        executorService.close();
        for(var metric : statsCollector.stats()) {
            switch (metric.getKey()) {
                case "sum" -> {
                    Assertions.assertEquals(metric.getValue().size(), 100);
                    Assertions.assertTrue(metric.getValue().contains(6.949999999999999));
                }
                case "max" -> {
                    Assertions.assertEquals(metric.getValue().size(), 100);
                    Assertions.assertTrue(metric.getValue().contains(5.1));
                }
                case "avg" -> {
                    Assertions.assertEquals(metric.getValue().size(), 100);
                    Assertions.assertTrue(metric.getValue().contains(1.39));
                }
                case "min" -> {
                    Assertions.assertEquals(metric.getValue().size(), 100);
                    Assertions.assertTrue(metric.getValue().contains(0.05));
                }
            }
        }
    }
}
