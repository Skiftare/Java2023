package edu.project4.geometry;

import edu.project4.color.StandardColourGenerator;
import static edu.project4.helperCore.Merging.mergePoints;

public class Rectangle {
    private final int width;
    private final int height;
    private final Point[][] points;


    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
        this.points = new Point[width][height];
        StandardColourGenerator standartPoints = new StandardColourGenerator();
        for(int i = 0;i<width;i++){
            for(int j = 0;j<height;j++){
                points[i][j] = new Point(standartPoints.getBlackColour(),i,j,0);
            }
        }
    }

    public boolean isPointInside(Point incomePoint) {
        int x = incomePoint.getX();
        int y = incomePoint.getY();

        return x >= 0 && x <= width && y >= 0 && y <= height;
    }
    public void addPoint(Point incomePoint){
        int posX = incomePoint.getX();
        int posY = incomePoint.getY();
        if(points[posX][posY].getHits() == 0) {
            points[posX][posY] = incomePoint;
        } else {
            Point resultPoint = mergePoints(points[posX][posY], incomePoint);
            points[posX][posY] = resultPoint;
        }
    }

}
