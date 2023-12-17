package edu.hw10.task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FibCalculatorTest {
    @Test
    @DisplayName("Cached-класс делает то самое, что и некэшированный класс")
    public void testThatGetClassAndReturnedResultFromMethodsOfCachedClass() {
        //given: calculator & CacheProxy
        FibCalculatorImpl calculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(calculator, calculator.getClass());
        int numberN = 10;
        proxy.fib(0);

        //when: we calc i-th fib number
        long onlineResult = calculator.fib(numberN);
        long cachedResult = proxy.fib(numberN);

        //then: they are equal
        Assertions.assertEquals(onlineResult, cachedResult);
    }

    @Test
    @DisplayName("Cahed-класс работает по нужной нам формуле")
    public void testThatGetClassAndReturnedApprovalOfNeededRealization() {
        //given: calculator & CacheProxy
        FibCalculatorImpl calculator = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(calculator, calculator.getClass());
        proxy.fib(0);
        int expectedResultForN = 6765;
        int numberN = 20;

        //when: we calc i-th fib number
        long cachedResult  = proxy.fib(numberN);

        //then: it is expected
        Assertions.assertEquals(cachedResult, expectedResultForN);
    }

}
