package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class LongRandomGenerator implements FieldRandomGenerator<Long> {
    @Override
    public Long random(Annotation[] annotations) {
        long max = Long.MAX_VALUE;
        long min = Long.MIN_VALUE;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                max = (long) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min maxAnnotation = (Min) annotation;
                min = (long) maxAnnotation.value();
            }
        }
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
