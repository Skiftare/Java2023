package edu.hw7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4SingleThreadTest {
    @Test
    void testThatGetIterationsAndReturnedCountPiWithGoodPrecision() {
        //given: iterations
        long iterationsPerThread = (long) 1e7;
        //when: we calk pi
        double pi = Task4SingleThread.calckPi(iterationsPerThread);
        //then: it has good precision
        assertEquals(Math.PI, pi, 0.01);
    }



    @Test
    void testThatGetHugeAmountIterationsAndReturnedCountPiWithVeryGoodPrecision() {
        //given: more iterations
        long iterationsPerThread = (long) 1e9;
        //when: we calk pi
        double pi = Task4SingleThread.calckPi(iterationsPerThread);

        //then: it has better precision
        assertEquals(Math.PI, pi, 0.001);
    }
}
