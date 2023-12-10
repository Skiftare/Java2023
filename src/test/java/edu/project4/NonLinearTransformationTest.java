package edu.project4;

import edu.project4.components.Point;
import edu.project4.transformation.nonlinear.variations.DiskTransformation;
import edu.project4.transformation.nonlinear.variations.HeartTransformation;
import edu.project4.transformation.nonlinear.variations.PolarTransformation;
import edu.project4.transformation.nonlinear.variations.SinusoidalTransformation;
import edu.project4.transformation.nonlinear.variations.SphericalTransformation;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonLinearTransformationTest {
    DiskTransformation disk = new DiskTransformation();
    SphericalTransformation sphere = new SphericalTransformation();
    SinusoidalTransformation sinus = new SinusoidalTransformation();
    PolarTransformation polar = new PolarTransformation();
    HeartTransformation heart = new HeartTransformation();
    DecimalFormat decimalFormat = new DecimalFormat("#.###");
  private double r(double x){
      return Double.parseDouble(decimalFormat.format(x));
  }
    private static final List<Point> points = Arrays.asList(
        new Point(1, 1),
        new Point(0.3465, 0.42356),
        new Point(-0.4, 5),
        new Point(-2, -0.2),
        new Point(0, 0)
    );


    @Test
    public void testThatGetArrayOfPointsAndReturnedDiskTransformedPoints() {



        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(-0.16, -0.06),
            new Point(0.18, -0.04),
            new Point(0.01, 0.47),
            new Point(0, 0.03),
            new Point(NaN, NaN)
        );

        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = disk.apply(points.get(i));

           assertEquals(expectedTransformedPoints.get(i).x(), transformedPoint.x(), 0.01);
           assertEquals(expectedTransformedPoints.get(i).y(), transformedPoint.y(), 0.01);
        }
    }
    @Test
    public void testThatGetArrayOfPointsAndReturnedHeartTransformedPoints() {



        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(1.26, -0.62),
            new Point(0.25, -0.48),
            new Point(4.56, 2.07),
            new Point(0.33, -1.98),
            new Point(0.0, 0.0)
        );

        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = heart.apply(points.get(i));
            assertEquals(expectedTransformedPoints.get(i).x(), transformedPoint.x(), 0.01);
            assertEquals(expectedTransformedPoints.get(i).y(), transformedPoint.y(), 0.01);
        }
    }
    @Test
    public void testThatGetArrayOfPointsAndReturnedSphereTransformedPoints() {



        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.5, 0.5),
            new Point(1.15, 1.41),
            new Point(-0.01, 0.19),
            new Point(-0.49, -0.04),
            new Point(NaN, NaN)
        );

        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = sphere.apply(points.get(i));
            assertEquals(expectedTransformedPoints.get(i).x(), transformedPoint.x(), 0.01);
            assertEquals(expectedTransformedPoints.get(i).y(), transformedPoint.y(), 0.01);
        }
    }
    @Test
    public void testThatGetArrayOfPointsAndReturnedSinusTransformedPoints() {



        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.84, 0.84),
            new Point(0.33, 0.41),
            new Point(-0.38, -0.95),
            new Point(-0.9, -0.19),
            new Point(0.0, 0.0)
        );

        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = sinus.apply(points.get(i));
            assertEquals(expectedTransformedPoints.get(i).x(), transformedPoint.x(), 0.01);
            assertEquals(expectedTransformedPoints.get(i).y(), transformedPoint.y(), 0.01);
        }
    }
    @Test
    public void testThatGetArrayOfPointsAndReturnedPolarTransformedPoints() {



        List<Point> expectedTransformedPoints = Arrays.asList(
            new Point(0.25, 0.41),
            new Point(0.28, -0.45),
            new Point(0.52, 4.01),
            new Point(-0.96, 1),
            new Point(0.0, -1.0)
        );

        for (int i = 0; i < points.size(); i++) {
            Point transformedPoint = polar.apply(points.get(i));
            assertEquals(expectedTransformedPoints.get(i).x(), transformedPoint.x(), 0.01);
            assertEquals(expectedTransformedPoints.get(i).y(), transformedPoint.y(), 0.01);
        }
    }

}
