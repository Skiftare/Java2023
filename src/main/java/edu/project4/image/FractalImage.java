package edu.project4.image;

import edu.project4.components.Pixel;
import java.awt.Color;

public class FractalImage {
    private static final String EXCEPTION_COORDINATES = "Invalid coordinates";
    private final Pixel[] data;
    private final int width;
    private final int height;

    public FractalImage(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new Pixel[width * height];
        for (int i = 0; i < width * height; i++) {
            data[i] = new Pixel(Color.BLACK, 0);
        }

    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public void setPixel(int x, int y, Pixel pixel) {
        if (contains(x, y)) {
            int index = y * width + x;
            data[index] = pixel;
        } else {
            throw new IllegalArgumentException(EXCEPTION_COORDINATES);
        }
    }

    public Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            int index = y * width + x;
            return data[index];
        }
        throw new IllegalArgumentException(EXCEPTION_COORDINATES);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
