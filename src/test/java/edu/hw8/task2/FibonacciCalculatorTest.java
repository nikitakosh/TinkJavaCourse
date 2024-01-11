package edu.hw8.task2;

import edu.task2.FibonacciCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FibonacciCalculatorTest {

    @Test
    @DisplayName("calculate fibonacci numbers")
    public void calculateFibonacciNumbers() {
        FibonacciCalculator fibonacciCalculator = new FibonacciCalculator();
        List<Long> fibonacciNumbers =  fibonacciCalculator.calculate(10);
        List<Long> actualFibonacciNumbers = List.of(
                1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L
        );
        for (Long fibonacciNumber : fibonacciNumbers) {
            Assertions.assertTrue(actualFibonacciNumbers.contains(fibonacciNumber));
        }
    }
}
