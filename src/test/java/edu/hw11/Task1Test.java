package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class Task1Test {

    @Test
    @DisplayName("create new class with override toString")
    public void createNewClass() {
        DynamicType.Unloaded<Object> unloadedClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make();
        Class<?> loadedClass = unloadedClass.load(getClass().getClassLoader()).getLoaded();
        try {
            Assertions.assertEquals("Hello, ByteBuddy!", loadedClass.getConstructor().newInstance().toString());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
