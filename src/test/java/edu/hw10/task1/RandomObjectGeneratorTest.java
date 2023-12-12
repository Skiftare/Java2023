package edu.hw10.task1;

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
        CustomClass randomObject = generator.nextObject(CustomClass.class, null);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(randomObject instanceof CustomClass);
    }

    @Test
    public void testNextObjectWithNotNullAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithNotNullAnnotation randomObject = generator.nextObject(ObjectWithNotNullAnnotation.class, null);

        Assertions.assertNotNull(randomObject);
        Assertions.assertNotNull(randomObject.getProperty());
    }

    @Test
    public void testNextObjectWithMinAndMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMinAndMaxAnnotation randomObject = generator.nextObject(ObjectWithMinAndMaxAnnotation.class, null);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(randomObject.getProperty() >= randomObject.getMinValue());
        Assertions.assertTrue(randomObject.getProperty() <= randomObject.getMaxValue());
    }

    @Test
    public void testNextObjectWithMinAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMinAnnotation randomObject = generator.nextObject(ObjectWithMinAnnotation.class, null);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(randomObject.getProperty() >= randomObject.getMinValue());
    }

    @Test
    public void testNextObjectWithMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMaxAnnotation randomObject = generator.nextObject(ObjectWithMaxAnnotation.class, null);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(randomObject.getProperty() <= randomObject.getMaxValue());
    }

    private static class CustomClass {
        // Custom class
    }

    private static class ObjectWithNotNullAnnotation {
        @NotNull
        private String property;

        public String getProperty() {
            return property;
        }
    }

    private static class ObjectWithMinAndMaxAnnotation {
        @Min(0)
        @Max(10)
        private int property;
        private int minValue;
        private int maxValue;

        public int getProperty() {
            return property;
        }

        public int getMinValue() {
            return minValue;
        }

        public int getMaxValue() {
            return maxValue;
        }
    }

    private static class ObjectWithMinAnnotation {
        @Min(0)
        private int property;
        private int minValue;

        public int getProperty() {
            return property;
        }

        public int getMinValue() {
            return minValue;
        }
    }

    private static class ObjectWithMaxAnnotation {

        private int property;
        @Max (10)
        private int maxValue;

        public int getProperty() {
            return property;
        }

        public int getMaxValue() {
            return maxValue;
        }
    }
}
