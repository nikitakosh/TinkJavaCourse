package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;

public interface FieldRandomGenerator<T> {

    static FieldRandomGenerator<?> getFieldRandomGenerator(Class<?> fieldType) {
        return switch (fieldType.getSimpleName()) {
            case "Integer" -> new IntegerRandomGenerator();
            case "Double" -> new DoubleRandomGenerator();
            case "Float" -> new FloatRandomGenerator();
            case "Boolean" -> new BooleanRandomGenerator();
            case "String" -> new StringRandomGenerator();
            case "Long" -> new LongRandomGenerator();
            default -> throw new RuntimeException("unknown field");
        };
    }

    T random(Annotation[] annotations);
}
