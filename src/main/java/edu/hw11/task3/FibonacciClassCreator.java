package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;

public class FibonacciClassCreator {
    private static final String CLASS_NAME = "FibonacciCalculator";
    private static final String FUNCTION_NAME = "fib";

    private FibonacciClassCreator() {
    }

    public static Class<?> createFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name(CLASS_NAME)
            .defineMethod(FUNCTION_NAME, long.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(
                new FibonacciMethodCreator()
            ))
            .make()
            .load(FibonacciClassCreator.class.getClassLoader())
            .getLoaded();
    }

}
