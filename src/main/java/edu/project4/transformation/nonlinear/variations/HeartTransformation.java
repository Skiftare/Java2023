package edu.project4.transformation.nonlinear.variations;

import edu.project4.transformation.Transformation;
import edu.project4.components.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt (x * x + y * y);
        double theta = atan2 (y, x);
        double newX = r * sin (theta * r);
        double newY = -r * cos (theta * r);

        return new Point(newX, newY);
    }
}
