package edu.hw10.task1.generators;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class StringRandomGenerator implements FieldRandomGenerator<String> {

    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 1;
    public static final int BOTTOM_ASCII_BOUND = 97;
    public static final int TOP_ASCII_BOUND = 123;

    @Override
    public String random(Annotation[] annotations) {
        long max = MAX_LENGTH;
        long min = MIN_LENGTH;
        boolean isNull = true;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Max.class)) {
                Max maxAnnotation = (Max) annotation;
                max = (long) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(Min.class)) {
                Min maxAnnotation = (Min) annotation;
                min = (long) maxAnnotation.value();
            }
            if (annotation.annotationType().equals(NotNull.class)) {
                isNull = false;
            }
        }
        if (isNull) {
            return null;
        }
        long length = ThreadLocalRandom.current().nextLong(min, max);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) ThreadLocalRandom.current().nextInt(BOTTOM_ASCII_BOUND, TOP_ASCII_BOUND));
        }
        return stringBuilder.toString();
    }
}
