package edu.project4.nonlinear;

import edu.project4.Transformation;
import edu.project4.components.Point;

public class SphericalTransformation implements Transformation {

    public Point apply(Point point) {
        double x = point.x() / (point.x() * point.x() + point.y() * point.y());
        double y = point.y() / (point.x() * point.x() + point.y() * point.y());
        return new Point(x, y);
    }


}
