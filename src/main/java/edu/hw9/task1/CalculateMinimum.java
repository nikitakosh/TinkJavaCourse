package edu.hw9.task1;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class CalculateMinimum implements Callable<Double> {

    private final Double[] data;

    public CalculateMinimum(Double[] data) {
        this.data = data;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).min(Double::compareTo).orElseThrow(() -> new EmptyDataException("data is empty"));
    }
}
