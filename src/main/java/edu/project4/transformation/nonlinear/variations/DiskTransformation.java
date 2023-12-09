package edu.project4.transformation.nonlinear.variations;

import edu.project4.transformation.Transformation;
import edu.project4.components.Point;
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
        double newX = sin(PI*sqrt(pow(x,2)+pow(y,2)))*atan(y/x)/PI;
        double newY = cos(PI*sqrt(pow(x,2)+pow(y,2)))*atan(y/x)/PI;

        return new Point(newX, newY);
    }
}
