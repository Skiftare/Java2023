package edu.hw2;

public class Square extends Rectangle {
    private int width;
    private int height;
    public Square(int side){
        super(side, side);
        width = side;
        height = side;
    }
    public Square(int side, int side1){
        super(side, side1);
        width = side;
        height = side1;
    }
    Square setWidth(int width) {
        return new Square(width, height);
    }

    Square setHeight(int height) {
        return new Square(width, height);
    }


    public Square setSide(int side){
        return new Square(side, height);
    }

}
