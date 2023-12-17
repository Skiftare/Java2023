package edu.hw10.task1;

import edu.hw10.task1.testClasses.ObjectWithMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMethod;
import edu.hw10.task1.testClasses.ObjectWithMinAndMaxAnnotation;
import edu.hw10.task1.testClasses.ObjectWithMinAnnotation;
import edu.hw10.task1.testClasses.ObjectWithNotNullAnnotation;
import edu.hw10.task1.testClasses.RecordClassForTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class RandomObjectGeneratorTest {

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedString() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        String randomString = generator.nextObject(String.class);

        //then: we get expected class
        Assertions.assertNotNull(randomString);
        Assertions.assertTrue(randomString instanceof String);
    }

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedInteger() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        Integer randomInteger = generator.nextObject(Integer.class);

        //then: we get expected class
        Assertions.assertNotNull(randomInteger);
        Assertions.assertTrue(randomInteger instanceof Integer);
    }


    @Test
    public void testThatGetGeneratorAndReturnedGeneratedObjectWithNotNullAnnotation() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        ObjectWithNotNullAnnotation randomObject = generator.nextObject(ObjectWithNotNullAnnotation.class);

        //then: we get expected class
        Assertions.assertNotNull(randomObject);
        Assertions.assertNotNull(randomObject.getProperty());
    }

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedObjectWithMinAndMaxAnnotation() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        Exception exception = assertThrows(
            RuntimeException.class,()-> {
                generator.nextObject(ObjectWithMinAndMaxAnnotation.class);
            });
        //then: we get exception: min = 100, max  = 8
        Assertions.assertEquals(exception.getMessage(), "Unsupported type");
    }

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedObjectWithMinAnnotation() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        ObjectWithMinAnnotation randomObject = generator.nextObject(ObjectWithMinAnnotation.class);

        //then: we get expected class
        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue( randomObject.getMinValue() >= 5);
    }

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedObjectWithMaxAnnotation() {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        ObjectWithMaxAnnotation randomObject = generator.nextObject(ObjectWithMaxAnnotation.class);

        //then: we get expected class
        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(100 >= randomObject.getMaxValueShort());
    }
    @Test
    public void testThatGetGeneratorAndReturnedGeneratedRecordClass() throws Exception {
        //given: class & generator
        RandomObjectGenerator generator = new RandomObjectGenerator();

        //when: we generate object
        RecordClassForTest randomObject = generator.nextObject(RecordClassForTest.class);

        //then: we get expected class
        Assertions.assertNotNull(randomObject);
        Assertions.assertTrue(10 >= randomObject.maxMinRecordLong());
        Assertions.assertTrue(0 <= randomObject.maxMinRecordLong());
    }

    @Test
    public void testThatGetGeneratorAndReturnedGeneratedObjectWithMethodAndReturnedGeneratedObjectByTisMethod() {
        // given: class & methodName
        Class<ObjectWithMethod> clazz = ObjectWithMethod.class;
        String methodName = "create";
        RandomObjectGenerator generator = new RandomObjectGenerator();

        // When we generate class
        ObjectWithMethod result = generator.nextObject(clazz, methodName);

        // Then String is not null (is generated value)
        assertTrue(result.getJustN() != null);
    }


}




