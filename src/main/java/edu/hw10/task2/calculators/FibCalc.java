package edu.hw10.task2.calculators;

public class FibCalc implements FibCalculator {
    @Override
    public long fibCachePersist(int number) {
        return fib(number);
    }

    @Override
    public long fibCacheNotPersist(int number) {
        return fib(number);
    }

    @Override
    public long fibNotCache(int number) {
        return fib(number);
    }

    public long fib(int number) {
        if (number == 0) {
            return 0;
        }
        if (number <= 2) {
            return 1;
        }
        return fib(number - 1) + fib(number - 2);
    }
}
