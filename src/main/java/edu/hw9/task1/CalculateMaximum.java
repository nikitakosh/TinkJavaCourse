package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class CalculateMaximum implements Callable<Double> {
    private final Double[] data;

    public CalculateMaximum(Double[] data) {
        this.data = data;
    }

    @Override
    public Double call() throws Exception {
        return Arrays.stream(data).max(Double::compareTo).orElseThrow(() -> new EmptyDataException("data is empty"));
    }
}
