package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;
import org.jetbrains.annotations.NotNull;

public class BooleanRandomGenerator implements FieldRandomGenerator<Boolean> {
    @Override public Boolean random(Annotation[] annotations) {
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(NotNull.class)) {
                isNull = false;
                break;
            }
        }
        if (isNull) {
            return null;
        }
        return ThreadLocalRandom.current().nextBoolean();
    }
}
