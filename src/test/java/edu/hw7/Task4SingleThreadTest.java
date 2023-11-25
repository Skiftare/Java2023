package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4SingleThreadTest {
    @Test
    void testThatGetIterationsAndReturnedCountPiWithGoodPrecision() {
        long iterationsPerThread = (long) 1e7;

        double pi = Task4SingleThread.calckPi(iterationsPerThread);

        // Assert that the calculated pi value is within a small range of the actual pi value
        assertEquals(Math.PI, pi, 0.01);
    }



    @Test
    void testThatGetHugeAmountIterationsAndReturnedCountPiWithVeryGoodPrecision() {
        long iterationsPerThread = (long) 1e9;

        double pi = Task4SingleThread.calckPi(iterationsPerThread);

        // Assert that the calculated pi value is within a small range of the actual pi value
        assertEquals(Math.PI, pi, 0.001);
    }
}
