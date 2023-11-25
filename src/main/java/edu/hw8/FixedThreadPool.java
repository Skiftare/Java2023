package edu.hw8;

import org.eclipse.jetty.util.thread.ThreadPool;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool implements ThreadPool {
    private final int numThreads;
    private ExecutorService executorService;

    public FixedThreadPool(int numThreads) {
        this.numThreads = numThreads;
    }


    public void start() {
        executorService = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    public void execute(@NotNull Runnable runnable) {
        executorService.execute(runnable);
    }


    public void close() {
        executorService.shutdown();
    }

    /*public static void main(String[] args) {
        FixedThreadPool threadPool = (FixedThreadPool) createFixedThreadPool(4);
        threadPool.start();

        for (int i = 0; i < 10; i++) {
            int n = i;
            threadPool.execute(() -> {
                long fib = calculateFibonacci(n);
                System.out.println("Fibonacci(" + n + ") = " + fib);
            });
        }

        threadPool.close();
    }*/

    private static ThreadPool createFixedThreadPool(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    /*private static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }(*/

    @Override
    public void join() throws InterruptedException {

    }

    @Override
    public int getThreads() {
        return 0;
    }

    @Override
    public int getIdleThreads() {
        return 0;
    }

    @Override
    public boolean isLowOnThreads() {
        return false;
    }
}
