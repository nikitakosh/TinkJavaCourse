package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class IntegerRandomGenerator implements FieldRandomGenerator<Integer> {
    @Override
    public Integer random(Annotation[] annotations) {
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                max = (int) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min maxAnnotation = (Min) annotation;
                min = (int) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(NotNull.class)) {
                isNull = false;
            }
        }
        if (isNull) {
            return null;
        }
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
