package edu.project4.helperCore;

import edu.project4.color.Color;
import edu.project4.geometry.Point;

public class Merging {
    private static double getMedianValue(double a, double b){
        return (a+b)/2;
    }
    public static Point mergePoints(Point p1, Point p2){
        if(p1.getX() == p2.getX()
            && p1.getY() == p2.getY()) {
            return p1;
        }
        return p1;
    }
    public static Color mergeColours(Color c1, Color c2){
        Color result = new Color(getMedianValue(c1.getRedComponent(), c2.getRedComponent()),
            getMedianValue(c1.getGreenComponent(), c2.getGreenComponent()),
            getMedianValue(c1.getBlueComponent(),c2.getBlueComponent()));
        return result;
    }


}
