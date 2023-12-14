package edu.hw9;

import edu.hw9.task1.Stats;
import edu.hw9.task1.StatsCollector;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StatsCollectorTest {
    @Test
    @DisplayName("Подтверждение корректности счёта")
    void testThatGetSomeDataAndReturnedMetricOfIt() throws InterruptedException {
        //given: test data & collector
        StatsCollector collector = new StatsCollector();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        //when: we run stat count
        executorService.submit(() -> collector.push("metric_name", new double[] {0.1, 0.05, 1.4, 5.1, 0.3}));

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        //then: we get stats as expected
        List<Stats> stats = collector.stats();
        for (Stats metric : stats) {
            assertThat(metric.metricName()).isEqualTo("metric_name");
            assertThat(metric.sum()).isCloseTo(6.95, within(0.001));
            assertThat(metric.average()).isCloseTo(1.39, within(0.001));
            assertThat(metric.min()).isCloseTo(0.05, within(0.001));
            assertThat(metric.max()).isCloseTo(5.1, within(0.001));
        }
    }

    private double[] generateTestDataForMultiThreadTesting() {
        // Generate test data
        int numValues = 2000000;
        double[] values = new double[numValues];
        for (int i = 0; i < numValues; i++) {
            values[i] = i;
        }
        return values;
    }

    private long measureTimeOfExecutionForMultiThread(int numThreads)
        throws InterruptedException {
        double[] values = generateTestDataForMultiThreadTesting();

        StatsCollector statsCollector = new StatsCollector();

        //measure time for cold-start of stats collector
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    private long measureTimeOfExecutionForSingleThread(int numThreads) {
        double[] values = generateTestDataForMultiThreadTesting();

        StatsCollector statsCollector = new StatsCollector();

        //measure time for cold-start of stats collector
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            statsCollector.push("metric", values);
        }
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    @Test
    @DisplayName("Подтверждение ускорения при многопоточной реализации")
    public void testThatGetSomeDataAndReturnedExecutionOptimizationApproveThanksMultiThread()
        throws InterruptedException {

        //given: data and threads
        int numThreads = 8;

        //when: run single & multi-thread realization
        long executionTimeSingleThread = measureTimeOfExecutionForSingleThread(numThreads);
        long executionTimeMultiThread = measureTimeOfExecutionForMultiThread(numThreads);

        //then: multi thread is faster
        assertTrue(executionTimeMultiThread < executionTimeSingleThread); // Multi-threaded execution is faster
    }

    @Test
    @DisplayName("Подтверждение синхронизации при многопотоном выполнении")
    public void testThatGetSomeDataAndReturnedExecutionSyncInMultiThreadApprove() throws InterruptedException {
        StatsCollector statsCollector = new StatsCollector();

        //given: data and threads
        int numThreads = 10;
        double[] values = generateTestDataForMultiThreadTesting();

        // when: Execute push() in multiple threads
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        for (int i = 0; i < numThreads; i++) {
            executorService.execute(() -> statsCollector.push("metric", values));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        // then: check if data is collected correctly (no de-sync)
        assertEquals(1, statsCollector.stats().size());
    }
}
