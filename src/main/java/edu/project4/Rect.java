package edu.project4;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        double rectRight = x + width;
        double rectBottom = y + height;
        return p.x() >= x && p.x() <= rectRight && p.y() >= y && p.y() <= rectBottom;
    }

}
