package edu.project4.geometry;

import edu.project4.color.Color;

public class Point {
    private final Color col;
    private final int hits;
    private final int x;
    private final int y;

    public Point(Color col, double x, double y, int hits) {
        this.col = col;
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
        this.hits = hits;
    }



    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Color getColour(){
        return col;
    }
    public int getHits(){
        return hits;
    }


}
