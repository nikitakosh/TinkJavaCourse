package edu.hw9.task1;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Callable;

public class CalculateSum implements Callable<Double> {

    private final Double[] data;

    public CalculateSum(Double[] data) {
        this.data = data;
    }

    @Override
    public Double call() {
        return Arrays.stream(data).reduce((double) 0, Double::sum);
    }
}
