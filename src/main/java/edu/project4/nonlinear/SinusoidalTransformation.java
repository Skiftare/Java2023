package edu.project4.nonlinear;

import edu.project4.Transformation;
import edu.project4.components.Point;

public class SinusoidalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = Math.sin(point.x());
        double y = Math.sin(point.y());
        return new Point(x, y);
    }

}
