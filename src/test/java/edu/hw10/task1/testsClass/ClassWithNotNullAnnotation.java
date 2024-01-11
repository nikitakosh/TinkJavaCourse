package edu.hw10.task1.testsClass;

import edu.hw10.task1.annotations.NotNull;

public record ClassWithNotNullAnnotation(String stringNotNull, String stringNull) {
    public ClassWithNotNullAnnotation(@NotNull String stringNotNull, String stringNull) {
        this.stringNotNull = stringNotNull;
        this.stringNull = stringNull;
    }
}
