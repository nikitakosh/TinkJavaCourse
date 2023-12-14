package edu.hw10.task1;

import edu.hw10.task1.testsClass.ClassWithMaxMinAnnotation;
import edu.hw10.task1.testsClass.ClassWithNotNullAnnotation;
import edu.hw10.task1.testsClass.PojoClass;
import edu.hw10.task1.testsClass.RecordClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class RandomObjectGeneratorTest {

    @Test
    @DisplayName("create object of record")
    public void nextRecordObject() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        RecordClass recordClass = randomObjectGenerator.nextObject(RecordClass.class);
        Assertions.assertInstanceOf(RecordClass.class, recordClass);
    }

    @Test
    @DisplayName("create POJO")
    public void nextPOJO() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        PojoClass pojoClass = randomObjectGenerator.nextObject(PojoClass.class);
        Assertions.assertInstanceOf(PojoClass.class, pojoClass);
    }

    @Test
    @DisplayName("create object by fabric method")
    public void createObjectByFabricMethod() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        PojoClass pojoClass = randomObjectGenerator.nextObject(PojoClass.class, "create");
        Assertions.assertInstanceOf(PojoClass.class, pojoClass);
        Assertions.assertEquals(123.12, pojoClass.getaDouble());
        Assertions.assertEquals(12.12f, pojoClass.getaFloat());
    }

    @RepeatedTest(5)
    @DisplayName("test max and min annotation")
    public void createObjectWithMaxMinAnnotation() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        ClassWithMaxMinAnnotation object = randomObjectGenerator.nextObject(ClassWithMaxMinAnnotation.class);
        Assertions.assertTrue(object.integer1() >= 0 && object.integer1() < 10);
        Assertions.assertTrue(object.integer2() >= 20 && object.integer2() < 30);
    }

    @Test
    @DisplayName("test NotNull annotation")
    public void createObjectWithNotNullAnnotation() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        ClassWithNotNullAnnotation object = randomObjectGenerator.nextObject(ClassWithNotNullAnnotation.class);
        Assertions.assertNotNull(object.stringNotNull());
        Assertions.assertNull(object.stringNull());
    }
}
