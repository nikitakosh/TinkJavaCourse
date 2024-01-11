package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class DoubleRandomGenerator implements FieldRandomGenerator<Double> {
    @Override
    public Double random(Annotation[] annotations) {
        double max = Integer.MAX_VALUE;
        double min = Integer.MIN_VALUE;
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                max = maxAnnotation.value();
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min maxAnnotation = (Min) annotation;
                min = maxAnnotation.value();
            }
            if (annotation.annotationType().equals(NotNull.class)) {
                isNull = false;
                break;
            }
        }
        if (isNull) {
            return null;
        }
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}