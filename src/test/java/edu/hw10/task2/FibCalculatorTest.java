package edu.hw10.task2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibCalculatorTest {
    @Test
    public void testFibonacci() {
        //given: calculator & CacheProxy
        FibCalculator calculator = new FibonacciCalculator();
        FibCalculator proxy = CacheProxy.create(calculator, FibCalculator.class);

        // when: we count i-th number of Fibonacci
        long result1 = proxy.fib(5);
        long result2 = proxy.fib(3);

        // then: we get expectedValues
        Assertions.assertThat(result1).isEqualTo(5);
        Assertions.assertThat(result2).isEqualTo(2);
    }
}
