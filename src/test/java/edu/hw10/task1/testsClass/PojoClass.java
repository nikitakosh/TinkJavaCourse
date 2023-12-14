package edu.hw10.task1.testsClass;

public class PojoClass {
    private final Double aDouble;
    private final Float aFloat;

    public PojoClass(Double aDouble, Float aFloat) {
        this.aDouble = aDouble;
        this.aFloat = aFloat;
    }

    public static PojoClass create() {
        return new PojoClass(123.12, 12.12f);
    }

    public Double getaDouble() {
        return aDouble;
    }

    public Float getaFloat() {
        return aFloat;
    }
}
