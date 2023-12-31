package edu.project4.transformation.nonlinear.variations;

import edu.project4.components.Point;
import edu.project4.transformation.Transformation;
import static edu.project4.image.ImageUtils.X_MAX;
import static java.lang.Math.PI;
import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double theta = PI * sqrt(pow(x, 2) + pow(y, 2));
        double newX = (sin(theta) * atan(y / x) / PI) / X_MAX;
        double newY = cos(theta) * atan(y / x) / PI;

        return new Point(newX, newY);
    }
}
