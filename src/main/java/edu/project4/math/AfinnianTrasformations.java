package edu.project4.math;

import edu.project4.geometry.Point;

public final class AfinnianTrasformations implements Transformation {

    public AfinnianTrasformations(double cosTheta, double v, int i, double sinTheta, double cosTheta1, int i1) {
    }

    public static AfinnianTrasformations newRotation(double theta) {
        final double cosTheta = Math.cos(theta);
        final double sinTheta = Math.sin(theta);
        return new AfinnianTrasformations(cosTheta, -sinTheta, 0, sinTheta,
            cosTheta, 0);
    }

    @Override
    public Point transformPoint(Point p) {
        // new Point(a * p.x() + b * p.y() + c, d * p.x() + e * p.y() + f);
        return null;
    }

}
