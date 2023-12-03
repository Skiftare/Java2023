package edu.project4.math;

import edu.project4.color.Color;

import edu.project4.geometry.Point;
import java.util.ArrayList;
import static edu.project4.helperCore.Merging.mergeColours;

public final class AfinnianTrasformations implements Transformation {
    private final double a, b, c, d, e, f;
    private final edu.project4.color.Color col;

    private boolean isValidAffineTransformation(double a, double b, double c, double d, double e, double f) {
        double determinant = a * e - b * d;
        return determinant != 0;

    }

    public AfinnianTrasformations initByArray(ArrayList<Double> incomeArray, Color col) {
        if (incomeArray.size() != 6) {
            throw new RuntimeException("Income coeff array is not valid");
        } else {
            return new AfinnianTrasformations(
                incomeArray.get(0),
                incomeArray.get(1),
                incomeArray.get(2),
                incomeArray.get(3),
                incomeArray.get(4),
                incomeArray.get(5), col
            );
        }
    }

    public AfinnianTrasformations(
        double a, double b, double c, double d,
        double e, double f, edu.project4.color.Color col
    ) {
        if (!isValidAffineTransformation(a, b, c, d, e, f)) {
            throw new RuntimeException("These parameters cannot be coefficients for an affine transformation");
        }

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
        if (p.getHits() == 0) {
            resultPoint = new Point(
                col,
                a * p.getX() + b * p.getY() + c,
                d * p.getX() + e * p.getY() + f,
                p.getHits()
            );
        } else {
            resultPoint = new Point(
                mergeColours(col, p.getColour()),
                a * p.getX() + b * p.getY() + c,
                d * p.getX() + e * p.getY() + f,
                p.getHits()
            );
        }
        return resultPoint;
    }

}
