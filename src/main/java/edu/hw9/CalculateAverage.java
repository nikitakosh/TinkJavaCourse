package edu.hw9;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class CalculateAverage implements Callable<Double> {

    private final Double[] data;

    public CalculateAverage(Double[] data) {
        this.data = data;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).mapToDouble(i -> i).average().getAsDouble();
    }
}
