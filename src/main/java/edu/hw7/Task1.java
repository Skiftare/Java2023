package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void incrementCounter() {
        counter.incrementAndGet();
    }

    public static void clearCounter() {
        counter.set(0);
    }
}
