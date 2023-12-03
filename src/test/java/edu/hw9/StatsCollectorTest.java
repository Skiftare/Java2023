package edu.hw9;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsCollectorTest {
    @Test
    @DisplayName("QQQ")
    void test() throws InterruptedException {
        StatsCollector collector = new StatsCollector();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.submit(() -> {
            collector.push("metric_name", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        List<Stats> stats = collector.stats();
        for (Stats metric : stats) {
            System.out.println("Metric Name: " + metric.getMetricName());
            System.out.println("Sum: " + metric.getSum());
            System.out.println("Average: " + metric.getAverage());
            System.out.println("Min: " + metric.getMin());
            System.out.println("Max: " + metric.getMax());
            System.out.println();
        }
    }
    @Test
    public void testPushPerformance() throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();

        // Generate test data
        int numThreads = 10;
        int numValues = 100000;
        double[] values = new double[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = i;
        }

        // Execute push() in multiple threads
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Check if data is collected correctly
        assertTrue(statsCollector.stats().size() == 1); // Only one metric is collected
        //assertTrue(statsCollector.stats().get(0).getSum() == numValues * numThreads); // Sum is correct

        // Measure and compare execution time
        long startTimeSingleThread = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            statsCollector.push("metric", values);
        }
        long endTimeSingleThread = System.currentTimeMillis();

        long startTimeMultiThread = System.currentTimeMillis();
        executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        long endTimeMultiThread = System.currentTimeMillis();

        long executionTimeSingleThread = endTimeSingleThread - startTimeSingleThread;
        long executionTimeMultiThread = endTimeMultiThread - startTimeMultiThread;

        System.out.println("Execution time (Single Thread): " + executionTimeSingleThread + " ms");
        System.out.println("Execution time (Multi Thread): " + executionTimeMultiThread + " ms");

        assertTrue(executionTimeMultiThread < executionTimeSingleThread); // Multi-threaded execution is faster
    }
}
