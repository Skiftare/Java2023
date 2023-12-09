package edu.project4.transformation.nonlinear.variations;

import edu.project4.components.Point;
import edu.project4.transformation.Transformation;

public class SphericalTransformation implements Transformation {
    public Point apply(Point point) {
        double x = point.x() / (point.x() * point.x() + point.y() * point.y());
        double y = point.y() / (point.x() * point.x() + point.y() * point.y());
        return new Point(x, y);
    }

}

