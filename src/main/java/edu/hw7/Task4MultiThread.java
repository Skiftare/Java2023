package edu.hw7;

import java.util.Random;

public class Task4MultiThread implements Runnable {
    private static long totalCount = 0;
    private static long circleCount = 0;
    private static final Object lock = new Object();

    private final long iterations;

    public Task4MultiThread(long iterations) {
        this.iterations = iterations;
    }

    @Override
    public void run() {
        Random random = new Random();
        long localTotalCount = 0;
        long localCircleCount = 0;

        for (long i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= 0.25) {
                localCircleCount++;
            }

            localTotalCount++;
        }

        synchronized (lock) {
            totalCount += localTotalCount;
            circleCount += localCircleCount;
        }
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

        return 4.0 * (circleCount / (double) totalCount);

    }
}
