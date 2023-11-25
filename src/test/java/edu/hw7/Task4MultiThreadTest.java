package edu.hw7;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task4MultiThreadTest {
    private final static Logger LOGGER = LogManager.getLogger();

    @Test
    void testThatGetNumOfThreadsAndIterationsAtAllAndReturnedCountPiWithGoodPrecision() throws InterruptedException {
        //given: numThreads and iterationsForAllThreads
        int numThreads = 4;
        long iterationsAtAllThreads = (long) 1e7;
        //when: we calculate pi with multithread
        double pi = Task4MultiThread.calcPi(numThreads, iterationsAtAllThreads/numThreads);

        //then: it has good precision
        assertEquals(Math.PI, pi, 0.01);
    }

    @Test
    void testThatGetDifferentNumOfThreadsAndIterationsAtAllAndReturnedCountPiWithSameGoodPrecision() throws InterruptedException {
        int numThreads1 = 2;
        int numThreads2 = 4;
        //given: numThreads (two numbers) and iterationsForAllThreads
        long iterationsAtAllThreads = (long) 1e8;
        //when: we calculate two vals of pi with multithread
        double pi1 = Task4MultiThread.calcPi(numThreads1, iterationsAtAllThreads/numThreads1);
        double pi2 = Task4MultiThread.calcPi(numThreads2, iterationsAtAllThreads/numThreads2);

        //then: both of them have good precision
        assertEquals(pi1, pi2, 0.01);
    }

    @Test
    void testThatGetDifferentNumOfThreadsAndHugeAmountOfIterationsAtAllAndReturnedCountPiVeryGoodPrecision() throws InterruptedException {
        //given: numThreads and huge iterationsForAllThreads
        int numThreads = 4;
        long iterationsAtAllThreads = (long) 1e9;
        //when: we calculate pi with multithread
        double pi = Task4MultiThread.calcPi(numThreads, iterationsAtAllThreads/numThreads);

        //then: it has very good precision
        assertEquals(Math.PI, pi, 0.001);
    }
    @Test
    void testThatGetSingleAndMultiRealizationAndReturnedThatMultiRealizationIsFaster() throws InterruptedException {
        //given: numThreads and huge iterationsForAllThreads
        int numThreads = 4;
        long iterationsAtAllThreads = (long) 1e10;
        //when: we calculate pi with multithreading and single-thread variants
        long startTimeSingleThread = System.nanoTime();
        double piSingleThread = Task4MultiThread.calcPi(1, iterationsAtAllThreads);
        long endTimeSingleThread = System.nanoTime();

        long startTimeMultiThread = System.nanoTime();
        double piMultiThread = Task4MultiThread.calcPi(numThreads, iterationsAtAllThreads/numThreads);
        long endTimeMultiThread = System.nanoTime();

        long singleThreadExecutionTime = endTimeSingleThread - startTimeSingleThread;
        long multiThreadExecutionTime = endTimeMultiThread - startTimeMultiThread;

        double speedup = (double) singleThreadExecutionTime / multiThreadExecutionTime;
        double error = Math.abs(piSingleThread - piMultiThread);

        LOGGER.info("Average speedup: " + speedup);
        LOGGER.info("Error: " + error);

        //then: the multithreaded execution is faster and has a lower error
        assertTrue(multiThreadExecutionTime < singleThreadExecutionTime);
        assertTrue(error < 0.01);
    }
}
