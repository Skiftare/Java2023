package edu.project4.transformation.nonlinear.variations;

import edu.project4.components.Point;
import edu.project4.transformation.Transformation;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = Math.sin(point.y());
        return new Point(x, y);
    }

}
