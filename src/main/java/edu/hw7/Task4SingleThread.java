package edu.hw7;

import java.util.Random;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4SingleThread {

    private static final double FOUR_VAL_FOR_MONTE_KARLO = 4.0;
    private static final double HALF_ONE_FOR_POINT_GENERATOR = 0.5;
    private static final double QUARTER_ONE_FOR_RADIUS_IN_GENERATOR = 0.25;
    private static final int TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA = 2;

    static double calckPi(long iterations) {

        long totalCount = 0;
        long circleCount = 0;

        Random random = new Random();

        for (long i = 0; i < iterations; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            double radius = Math.pow(x - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA)
                + Math.pow(y - HALF_ONE_FOR_POINT_GENERATOR, TWO_AS_DEGREE_OF_POW_FOR_CIRCLE_FORMULA);
            if (radius <= QUARTER_ONE_FOR_RADIUS_IN_GENERATOR) {
                circleCount++;
            }

            totalCount++;
        }
        return FOUR_VAL_FOR_MONTE_KARLO * (circleCount / (double) totalCount);

    }
}
