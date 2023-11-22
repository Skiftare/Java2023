package edu.hw7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @BeforeEach
    void cleanUpData(){
        Task1.clearCounter();
    }
    @Test
    @DisplayName("100 потоков, каждый увеличивает на 1000")
    public void testThatGetManyThreadsAndManyIncrementsPerThreadsAndReturnedMultipleByIncrementing()
        throws InterruptedException {
        //given: numThreads > 1, increments > 1
        int numThreads = 100;
        int numIncrementsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];
        //when: create (num) threads, in each (increments) times we make ++ to value
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIncrementsPerThread; j++) {
                    Task1.incrementCounter();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        //then: get in value: num*increments
        assertThat(Task1.counter.get()).isEqualTo(numThreads * numIncrementsPerThread);
    }
    @Test
    @DisplayName("1 поток, увеличивает на 1000")
    public void testThatGetOneThreadAndManyIncrementsPerThreadsAndReturnedMultipleByIncrementing()
        throws InterruptedException {
        //given: numThreads = 1, increments > 1
        int numThreads = 1;
        int numIncrementsPerThread = 1000;

        Thread[] threads = new Thread[numThreads];
        //when: create (num) thread, in each (increments) times we make ++ to value
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIncrementsPerThread; j++) {
                    Task1.incrementCounter();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        //then: get in value: (increments)
        assertThat(Task1.counter.get()).isEqualTo(numThreads * numIncrementsPerThread);
    }


    @Test
    @DisplayName("1000 поток, увеличивает на 1")
    public void testThatGetManyThreadAndOneIncrementsPerThreadsAndReturnedMultipleByIncrementing()
        throws InterruptedException {
        //given: numThreads >1 increments = 1
        int numThreads = 1000;
        int numIncrementsPerThread = 1;

        Thread[] threads = new Thread[numThreads];
        //when: create (num) threads, in them 1 times we make ++ to value
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < numIncrementsPerThread; j++) {
                    Task1.incrementCounter();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }
        //then: get in value: (num)
        assertThat(Task1.counter.get()).isEqualTo(numThreads * numIncrementsPerThread);
    }
}
