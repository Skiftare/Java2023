package edu.hw10.task1;

import edu.hw10.task1.testClasses.ObjectWithMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMinAndMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMinAnnotation;
import edu.hw10.task1.testClasses.ObjectWithNotNullAnnotation;
import edu.hw10.task1.testClasses.RecordClassForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;

public class RandomObjectGeneratorTest {

    @Test
    public void testNextObjectWithString() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        String randomString = generator.nextObject(String.class);

        Assertions.assertNotNull(randomString);
        Assertions.assertTrue(randomString instanceof String);
    }

    @Test
    public void testNextObjectWithInteger() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        Integer randomInteger = generator.nextObject(Integer.class);
        Assertions.assertNotNull(randomInteger);
        Assertions.assertTrue(randomInteger instanceof Integer);
    }


    @Test
    public void testNextObjectWithNotNullAnnotation() {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithNotNullAnnotation randomObject = generator.nextObject(ObjectWithNotNullAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertNotNull(randomObject.getProperty());
    }

    @Test
    public void testNextObjectWithMinAndMaxAnnotation() {

        RandomObjectGenerator generator = new RandomObjectGenerator();
        Exception exception = assertThrows(
            RuntimeException.class,()-> {
                generator.nextObject(ObjectWithMinAndMaxAnnotation.class);
            });

        Assertions.assertEquals(exception.getMessage(), "Unsupported type");
    }

    @Test
    public void testNextObjectWithMinAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMinAnnotation randomObject = generator.nextObject(ObjectWithMinAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue( randomObject.getMinValue() >= 5);
    }

    @Test
    public void testNextObjectWithMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        ObjectWithMaxAnnotation randomObject = generator.nextObject(ObjectWithMaxAnnotation.class);

        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(100 >= randomObject.getMaxValueShort());
    }
    @Test
    public void testRecordNextObjectWithMaxAnnotation() throws Exception {
        RandomObjectGenerator generator = new RandomObjectGenerator();
        RecordClassForTest randomObject = generator.nextObject(RecordClassForTest.class);

        Assertions.assertNotNull(randomObject);
        //@Max(10) @Min(0) @NotNull Long maxMinRecordLong
        System.out.println(randomObject.maxMinRecordLong());
        Assertions.assertTrue(10 >= randomObject.maxMinRecordLong());
        Assertions.assertTrue(0 <= randomObject.maxMinRecordLong());
    }


}

