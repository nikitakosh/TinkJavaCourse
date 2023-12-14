package edu.hw10.task2;

import edu.hw10.task2.calculators.FibCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CacheInvocationHandlerTest {

    @Test
    @DisplayName("find cache annotation")
    public void findCacheAnnotation() {
        CacheInvocationHandler cacheInvocationHandler = new CacheInvocationHandler(new Object());
        Cache cacheAnnotationPersist;
        Cache cacheAnnotationNotPersist;
        Cache notCacheAnnotation;
        try {
            cacheAnnotationPersist = cacheInvocationHandler
                .findCacheAnnotation(FibCalculator.class.getMethod("fibCachePersist", int.class));
            cacheAnnotationNotPersist = cacheInvocationHandler
                .findCacheAnnotation(FibCalculator.class.getMethod("fibCacheNotPersist", int.class));
            notCacheAnnotation = cacheInvocationHandler
                .findCacheAnnotation(FibCalculator.class.getMethod("fibNotCache", int.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(cacheAnnotationPersist.persist());
        Assertions.assertFalse(cacheAnnotationNotPersist.persist());
        Assertions.assertNull(notCacheAnnotation);
    }

    @Test
    @DisplayName("get key")
    public void getKey() {
        CacheInvocationHandler cacheInvocationHandler = new CacheInvocationHandler(new Object());
        String key;
        try {
            key = cacheInvocationHandler
                .getKey(FibCalculator.class.getMethod("fibCachePersist", int.class), new Object[]{10});
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("fibCachePersist 10", key);
    }
}
