package edu.hw10.task2;

import edu.hw10.task2.calculators.FibCalc;
import edu.hw10.task2.calculators.FibCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CacheProxyTest {
    private FibCalculator proxyFibCalculator;

    @BeforeEach
    public void setUp() {
        FibCalculator fibCalculator = new FibCalc();
        proxyFibCalculator = CacheProxy.create(fibCalculator, FibCalculator.class);
    }

    @Test
    @DisplayName("cache persist")
    public void cachePersist() {
        Assertions.assertEquals(proxyFibCalculator.fibCachePersist(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibCachePersist(6), 8);
        Assertions.assertEquals(proxyFibCalculator.fibCachePersist(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibCachePersist(6), 8);
    }

    @Test
    @DisplayName("cache not persist")
    public void cacheNotPersist() {
        Assertions.assertEquals(proxyFibCalculator.fibCacheNotPersist(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibCacheNotPersist(6), 8);
        Assertions.assertEquals(proxyFibCalculator.fibCacheNotPersist(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibCacheNotPersist(6), 8);
    }

    @Test
    @DisplayName("not cache")
    public void notCache() {
        Assertions.assertEquals(proxyFibCalculator.fibNotCache(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibNotCache(6), 8);
        Assertions.assertEquals(proxyFibCalculator.fibNotCache(5), 5);
        Assertions.assertEquals(proxyFibCalculator.fibNotCache(6), 8);
    }
}
