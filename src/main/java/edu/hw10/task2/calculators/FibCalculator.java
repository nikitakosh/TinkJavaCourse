package edu.hw10.task2.calculators;

import edu.hw10.task2.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fibCachePersist(int number);

    @Cache()
    long fibCacheNotPersist(int number);

    long fibNotCache(int number);
}
