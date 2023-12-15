package edu.hw11.task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;

public class FibonacciClassCreator {

    public static Class<?> createFibonacciClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCalculator")
            .defineMethod("fib", long.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new FibonacciInterception())
            .make()
            .load(FibonacciClassCreator.class.getClassLoader())
            .getLoaded();
    }
}
