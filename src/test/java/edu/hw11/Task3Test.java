package edu.hw11;

import java.lang.reflect.Method;
import edu.hw11.task3.MyClassLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Выполнение класса в байт-коде")
    public void testThatGetByteCodeAndReturnedResultOfExecutionByteCode() {
        try {
            //given: pos for fib number
            int n = 8;
            int expectedResult = 21;

            //when: we load class from bytecode
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> myClass =
                myClassLoader.loadClass("Fibonacci");
            Method staticMethod = myClass.getMethod("fibonacci", int.class);

            //then: we get expected number
            int realResult = (int) staticMethod.invoke(null, n);
            Assertions.assertEquals(expectedResult, realResult);
        } catch (Exception e) {
            ErrorLogger.createLogError(e.getMessage());
            assert false;
        }
    }
}
