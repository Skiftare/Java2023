package edu.hw8;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    @Test
    public void testThatGetFixedThreadPoolAndReturnsFirstNFibonacciNumbers() throws Exception {
        //given: numberOfThreads, numberOfTasks & FixedThreadPool
        int numberOfThreads = 4;
        int numberOfTasks = 10;

        FixedThreadPool pool = new FixedThreadPool(numberOfThreads);
        pool.start();

        CountDownLatch latch = new CountDownLatch(numberOfTasks); //Синхронизация
        List<Long> results = new ArrayList<>();
        //when: count i-th fib number. In case that List in unsyncronysed, we're using CountDownLatch
        for (int i = 0; i < numberOfTasks; i++) {
            int finalI = i;
            pool.execute(() -> {
                long fib = fibonacci(finalI);
                results.add(fib);
                latch.countDown();
            });
        }
        //then: wait for all tasks komplete, kill pool & assert all fib numbers
        latch.await(5, TimeUnit.SECONDS);
        pool.close();

        // Assert that all tasks have been executed
        Assertions.assertThat(results).hasSize(numberOfTasks);

        // Assert that the results are correct
        for (int i = 0; i < numberOfTasks; i++) {
            Assertions.assertThat(results.get(i)).isEqualTo(fibonacci(i));
        }
    }

    private static long fibonacci(int n) {
        if (n <= 1) return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }
}
