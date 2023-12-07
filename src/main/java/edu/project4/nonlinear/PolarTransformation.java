package edu.project4.nonlinear;

import edu.project4.Transformation;
import edu.project4.components.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double newX = Math.atan(y/x)/Math.PI;
        double newY = Math.sqrt(Math.pow(x,2)+Math.pow(y,2)) - 1;
        return new Point(newX, newY);
    }

}
