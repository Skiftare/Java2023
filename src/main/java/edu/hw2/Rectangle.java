package edu.hw2;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int side, int side1) {
        width = side;
        height = side1;
    }

    Rectangle setWidth(int width) {
        return new Rectangle(width, this.height);
    }

    Rectangle setHeight(int height) {
        return new Rectangle(this.width, height);
    }

    double area() {
        return width * height;
    }
}
