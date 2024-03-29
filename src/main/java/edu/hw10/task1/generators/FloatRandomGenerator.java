package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class FloatRandomGenerator implements FieldRandomGenerator<Float> {
    @Override
    public Float random(Annotation[] annotations) {
        float max = Integer.MAX_VALUE;
        float min = Integer.MIN_VALUE;
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                max = (float) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min maxAnnotation = (Min) annotation;
                min = (float) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(NotNull.class)) {
                isNull = false;
            }
        }
        if (isNull) {
            return null;
        }
        return ThreadLocalRandom.current().nextFloat(min, max);
    }
}
