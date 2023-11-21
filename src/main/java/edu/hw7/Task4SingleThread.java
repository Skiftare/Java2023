package edu.hw7;

import java.util.Random;

public class Task4SingleThread {
    double calckPi(long iterations) {

        long totalCount = 0;
        long circleCount = 0;

        Random random = new Random();

        for (long i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();

            if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= 0.25) {
                circleCount++;
            }

            totalCount++;
        }
        return 4.0 * (circleCount / (double) totalCount);

    }
}
