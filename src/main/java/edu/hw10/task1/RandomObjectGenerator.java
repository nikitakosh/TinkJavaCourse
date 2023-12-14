package edu.hw10.task1;

import edu.hw10.task1.generators.FieldRandomGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;

public class RandomObjectGenerator {

    public <T> T nextObject(Class<T> clazz, String fabricMethodName) {
        Method fabricMethod;
        try {
            fabricMethod = clazz.getMethod(fabricMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("not found fabric method");
        }
        fabricMethod.setAccessible(true);
        try {
            return clazz.cast(fabricMethod.invoke(clazz));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> clazz) {
        Constructor<?> constructor = getConstructor(clazz);
        Object[] parameters = new Object[constructor.getParameterCount()];
        int i = 0;
        for (Parameter parameter : constructor.getParameters()) {
            FieldRandomGenerator<?> fieldRandomGenerator =
                FieldRandomGenerator.getFieldRandomGenerator(parameter.getType());
            parameters[i] = (fieldRandomGenerator.random(parameter.getAnnotations()));
            i++;
        }
        try {
            return clazz.cast(constructor.newInstance(parameters));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Constructor<?> getConstructor(Class<T> clazz) {
        return Arrays.stream(clazz.getDeclaredConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElseThrow(() -> new RuntimeException("not found constructor"));
    }

}
