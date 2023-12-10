package edu.project4;

import edu.project4.components.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.nonlinear.variations.DiskTransformation;
import edu.project4.transformation.nonlinear.variations.HeartTransformation;
import edu.project4.transformation.nonlinear.variations.PolarTransformation;
import edu.project4.transformation.nonlinear.variations.SinusoidalTransformation;
import edu.project4.transformation.nonlinear.variations.SphericalTransformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static java.lang.Double.NaN;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;

public class NonLinearTransformationTest {
    private static final List<Point> points = Arrays.asList(
        new Point(1, 1),
        new Point(0.3465, 0.42356),
        new Point(-0.4, 5),
        new Point(-2, -0.2),
        new Point(0, 0)
    );

    DiskTransformation disk = new DiskTransformation();
    SphericalTransformation sphere = new SphericalTransformation();
    SinusoidalTransformation sinus = new SinusoidalTransformation();
    PolarTransformation polar = new PolarTransformation();
    HeartTransformation heart = new HeartTransformation();

    private List<Point> fillByTransformation(Transformation trans) {
        List<Point> realPoint = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = trans.apply(points.get(i));
            realPoint.add(transformedPoint);
        }
        return realPoint;
    }

    private void assertForOneCoord(double real, double expected) {
        assertThat(real).isCloseTo(expected, within(0.01));
    }

    private void assertThatMassivesOfCoordsAreQuiteSame(List<Point> real, List<Point> expected) {
        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = real.get(i);
            assertForOneCoord(transformedPoint.x(), expected.get(i).x());
            assertForOneCoord(transformedPoint.y(), expected.get(i).y());
        }
    }

    @Test
    public void testThatGetArrayOfPointsAndReturnedDiskTransformedPoints() {
        //given: list of income points & one nonlinear transformation
        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(-0.16, -0.06),
            new Point(0.18, -0.04),
            new Point(0.01, 0.47),
            new Point(0, 0.03),
            new Point(NaN, NaN)
        );

        //when: we apply it on the i-th point
        List<Point> realPoint = fillByTransformation(disk);

        //then: expectedTransformedPoints & realPoint contain same values
        assertThatMassivesOfCoordsAreQuiteSame(realPoint, expectedTransformedPoints);
    }

    @Test
    public void testThatGetArrayOfPointsAndReturnedHeartTransformedPoints() {
        //given: list of income points & one nonlinear transformation
        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(1.26, -0.62),
            new Point(0.25, -0.48),
            new Point(4.56, 2.07),
            new Point(0.33, -1.98),
            new Point(0.0, 0.0)
        );

        //when: we apply it on the i-th point
        List<Point> realPoint = fillByTransformation(heart);

        //then: expectedTransformedPoints & realPoint contain same values
        assertThatMassivesOfCoordsAreQuiteSame(realPoint, expectedTransformedPoints);

    }

    @Test
    public void testThatGetArrayOfPointsAndReturnedSphereTransformedPoints() {
        //given: list of income points & one nonlinear transformation
        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.5, 0.5),
            new Point(1.15, 1.41),
            new Point(-0.01, 0.19),
            new Point(-0.49, -0.04),
            new Point(NaN, NaN)
        );

        //when: we apply it on the i-th point
        List<Point> realPoint = fillByTransformation(sphere);

        //then: expectedTransformedPoints & realPoint contain same values
        assertThatMassivesOfCoordsAreQuiteSame(realPoint, expectedTransformedPoints);

    }

    @Test
    public void testThatGetArrayOfPointsAndReturnedSinusTransformedPoints() {
        //given: list of income points & one nonlinear transformation
        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.84, 0.84),
            new Point(0.33, 0.41),
            new Point(-0.38, -0.95),
            new Point(-0.9, -0.19),
            new Point(0.0, 0.0)
        );

        //when: we apply it on the i-th point
        List<Point> realPoint = fillByTransformation(sinus);

        //then: expectedTransformedPoints & realPoint contain same values
        assertThatMassivesOfCoordsAreQuiteSame(realPoint, expectedTransformedPoints);

    }

    @Test
    public void testThatGetArrayOfPointsAndReturnedPolarTransformedPoints() {
        //given: list of income points & one nonlinear transformation
        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.25, 0.41),
            new Point(0.28, -0.45),
            new Point(0.52, 4.01),
            new Point(-0.96, 1),
            new Point(0.0, -1.0)
        );

        //when: we apply it on the i-th point
        List<Point> realPoint = fillByTransformation(polar);

        //then: expectedTransformedPoints & realPoint contain same values
        assertThatMassivesOfCoordsAreQuiteSame(realPoint, expectedTransformedPoints);

    }

}
