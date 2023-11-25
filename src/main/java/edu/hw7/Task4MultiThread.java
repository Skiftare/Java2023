package edu.hw7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class Task4MultiThread implements Runnable {
    private static final AtomicLong totalCount = new AtomicLong(0);
    private static final AtomicLong circleCount = new AtomicLong(0);
    private final long iterations;
    private static final double FOUR_VAL_FOR_MONTE_KARLO = 4.0;
    private static final double HALF_ONE_FOR_POINT_GENERATOR = 0.5;
    private static final double QUARTER_ONE_FOR_RADIUS_IN_GENERATOR = 0.25;
    private static final int TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA = 2;

    public Task4MultiThread(long iterations) {
        this.iterations = iterations;
    }

    public static double calcPi(int numThreads, long iterationsPerThread) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executor.execute(new Task4MultiThread(iterationsPerThread));
        }

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);

        return FOUR_VAL_FOR_MONTE_KARLO * (circleCount.get() / (double) totalCount.get());
    }

    @Override
    public void run() {
        long localTotalCount = 0;
        long localCircleCount = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (long i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double radius = Math.pow(x - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA)
                + Math.pow(y - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA);
            if (radius <= QUARTER_ONE_FOR_RADIUS_IN_GENERATOR) {
                localCircleCount++;
            }

            localTotalCount++;
        }

        totalCount.addAndGet(localTotalCount);
        circleCount.addAndGet(localCircleCount);
    }
}
