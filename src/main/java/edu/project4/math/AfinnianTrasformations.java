package edu.project4.math;

import edu.project4.color.Colour;
import edu.project4.geometry.Point;
import static edu.project4.helperCore.Merging.mergeColours;

public final class AfinnianTrasformations implements Transformation {
    private final double a, b, c, d, e, f;
    private final Colour col;

    public AfinnianTrasformations(double a, double b, double c, double d,
        double e, double f, Colour col
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.col = col;
    }




    @Override
    public Point transformPoint(Point p) {
        Point resultPoint;
        if(p.getHits() == 0) {
            resultPoint = new Point(col,
                a * p.getX() + b * p.getY() + c,
                d * p.getX() + e * p.getY() + f,
                p.getHits());
        } else {
            resultPoint = new Point(mergeColours(col, p.getColour()),
                a * p.getX() + b * p.getY() + c,
                d * p.getX() + e * p.getY() + f,
                p.getHits());
        }
        return resultPoint;
    }


}
