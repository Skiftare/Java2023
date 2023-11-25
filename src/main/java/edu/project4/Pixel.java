package edu.project4;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;

    public Pixel(int r, int g, int b, int hitCount) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
    }

    // Getters and setters for r, g, b, and hitCount

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
