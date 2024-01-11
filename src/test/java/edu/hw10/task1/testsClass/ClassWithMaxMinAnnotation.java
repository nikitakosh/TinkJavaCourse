package edu.hw10.task1.testsClass;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public record ClassWithMaxMinAnnotation(Integer integer1, Integer integer2) {
    public ClassWithMaxMinAnnotation(
        @NotNull @Min(0) @Max(10) Integer integer1,
        @NotNull @Min(20) @Max(30) Integer integer2
    ) {
        this.integer1 = integer1;
        this.integer2 = integer2;
    }
}
