package edu.hw11;

import edu.hw11.task3.FibonacciClassCreator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Task3Test {

    @Test
    @DisplayName("create fibonacci calculator")
    public void createFibonacciCalculator() {
        try {
            Class<?> dynamicClass = FibonacciClassCreator.createFibonacciClass();
            Object instance = dynamicClass.getConstructor().newInstance();
            Method fibMethod = dynamicClass.getMethod("fib", int.class);
            long result = (long) fibMethod.invoke(instance, 10);
            System.out.println(result);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
