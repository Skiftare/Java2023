package edu.project4.nonlinear;

import edu.project4.Transformation;
import edu.project4.components.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class DiskTransformation implements Transformation {


    /*
        r = sqrt (x * x + y * y) * M_PI;
              theta = atan2 (y, x) / M_PI;
              newx = theta * sin (r);
              newy = theta * cos (r);
         */
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt (x * x + y * y) * Math.PI;
        double theta = atan2 (y, x) / Math.PI;
        double newx = theta * sin (r);
        double newy = theta * cos (r);
        return new Point(newx, newy);
    }
}
