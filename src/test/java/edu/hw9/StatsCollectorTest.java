package edu.hw9;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsCollectorTest {
    @Test
    @DisplayName("Подтверждение корректности счёта")
    void testThatGetSomeDataAndReturnedMetricOfIt() throws InterruptedException {
        //given: test data & collector
        StatsCollector collector = new StatsCollector();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //when: we run stat count
        executorService.submit(() -> {
            collector.push("metric_name", new double[]{0.1, 0.05, 1.4, 5.1, 0.3});
        });

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        //then: we get stats as expected
        List<Stats> stats = collector.stats();
        for (Stats metric : stats) {
            assertThat(metric.getMetricName()).isEqualTo("metric_name");
            assertThat(metric.getSum()).isCloseTo(6.95, within(0.001));
            assertThat(metric.getAverage()).isCloseTo(1.39, within(0.001));
            assertThat(metric.getMin()).isCloseTo(0.05, within(0.001));
            assertThat(metric.getMax()).isCloseTo(5.1, within(0.001));
        }
    }
    @Test
    @DisplayName("Подтверждение ускорения при многопоточной реализации")
    public void testThatGetSomeDataAndReturnedExecutionOptimizationApproveThanksMultiThread() throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();
        //given: data and threads
        // Generate test data
        int numThreads = 10;
        int numValues = 100000;
        double[] values = new double[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = i;
        }

        // when: Execute push() in multiple threads

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // then: check if data is collected correctly (no de-sync)
        assertTrue(statsCollector.stats().size() == 1); // Only one metric is collected

        // when: Execute push() in single threads
        long startTimeSingleThread = System.currentTimeMillis();
        for (int i = 0; i < numThreads; i++) {
            statsCollector.push("metric", values);
        }
        long endTimeSingleThread = System.currentTimeMillis();

        //count time for multi thread
        long startTimeMultiThread = System.currentTimeMillis();
        executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        long endTimeMultiThread = System.currentTimeMillis();

        //then: multi thread is faster
        long executionTimeSingleThread = endTimeSingleThread - startTimeSingleThread;
        long executionTimeMultiThread = endTimeMultiThread - startTimeMultiThread;

        assertTrue(executionTimeMultiThread < executionTimeSingleThread); // Multi-threaded execution is faster
    }
}
