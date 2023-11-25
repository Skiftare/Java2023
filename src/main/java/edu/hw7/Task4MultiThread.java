package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;

public class Task4MultiThread implements Runnable {
    private static final Object LOCK = new Object();
    private static long totalCount = 0;
    private static long circleCount = 0;
    private final long iterations;
    private static final double FOUR_VAL_FOR_MONTE_KARLO = 4.0;
    private static final double HALF_ONE_FOR_POINT_GENERATOR = 0.5;
    private static final double QUARTER_ONE_FOR_RADIUS_IN_GENERATOR = 0.25;
    private static final int TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA = 2;

    public Task4MultiThread(long iterations) {
        this.iterations = iterations;
    }

    public static double calcPi(int numThreads, long iterationsPerThread) throws InterruptedException {

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new Task4MultiThread(iterationsPerThread));
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            threads[i].join();
        }

        return FOUR_VAL_FOR_MONTE_KARLO * (circleCount / (double) totalCount);

    }

    @Override
    public void run() {
        //ThreadLocalRandom random = new ThreadLocalRandom();
        long localTotalCount = 0;
        long localCircleCount = 0;

        for (long i = 0; i < iterations; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();
            double radius = Math.pow(x - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA)
                + Math.pow(y - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA);
            if (radius <= QUARTER_ONE_FOR_RADIUS_IN_GENERATOR) {
                localCircleCount++;
            }

            localTotalCount++;
        }

        synchronized (LOCK) {
            totalCount += localTotalCount;
            circleCount += localCircleCount;
        }
    }
}
