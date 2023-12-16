package edu.hw10.task1;

import edu.hw10.task1.testClasses.ObjectWithMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMinAndMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMinAnnotation;
import edu.hw10.task1.testClasses.ObjectWithNotNullAnnotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RandomObjectGeneratorTest {

    @Test
    public void testNextObjectWithString() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        String randomString = generator.nextObject(String.class);

        Assertions.assertNotNull(randomString);
        Assertions.assertTrue(randomString instanceof String);
    }

    @Test
    public void testNextObjectWithInteger() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        Integer randomInteger = generator.nextObject(Integer.class);
        System.out.println(randomInteger);
        Assertions.assertNotNull(randomInteger);
        //Assertions.assertTrue(randomInteger instanceof Integer);
    }

    @Test
    public void testNextObjectWithCustomClass() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        CustomClass randomObject = generator.nextObject(CustomClass.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(randomObject instanceof CustomClass);
    }

    @Test
    public void testNextObjectWithNotNullAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithNotNullAnnotation randomObject = generator.nextObject(ObjectWithNotNullAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertNotNull(randomObject.getProperty());
    }

    @Test
    public void testNextObjectWithMinAndMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMinAndMaxAnnotation randomObject = generator.nextObject(ObjectWithMinAndMaxAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(0 <= randomObject.getMinValue());
        Assertions.assertTrue(100 >= randomObject.getMaxValue());
    }

    @Test
    public void testNextObjectWithMinAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMinAnnotation randomObject = generator.nextObject(ObjectWithMinAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue( randomObject.getMinValue()>= 5);
    }

    @Test
    public void testNextObjectWithMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMaxAnnotation randomObject = generator.nextObject(ObjectWithMaxAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(100 >= randomObject.getMaxValue());
    }

    private static class CustomClass {
        // Custom class
    }








}
