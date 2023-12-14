package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;
import java.util.concurrent.ThreadLocalRandom;

public class BooleanRandomGenerator implements FieldRandomGenerator<Boolean> {
    @Override
    public Boolean random(Annotation[] annotations) {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
