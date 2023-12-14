package edu.hw10.task1.testsClass;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;

public class ClassWithMaxMinAnnotation {
    private final Integer integer1;
    private final Integer integer2;

    public ClassWithMaxMinAnnotation(@Min(0) @Max(10) Integer integer1,  @Min(20) @Max(30) Integer integer2) {
        this.integer1 = integer1;
        this.integer2 = integer2;
    }

    public Integer getInteger1() {
        return integer1;
    }

    public Integer getInteger2() {
        return integer2;
    }
}
