package edu.project4;

import edu.project4.components.Pixel;
import java.awt.Color;

public record FractalImage(Pixel[] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[] data = new Pixel[width * height];
        for(int i = 0;i<width*height;i++){
            data[i] = new Pixel(Color.BLACK, 0);
        }
        return new FractalImage(data, width, height);
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    public void setPixel(int x, int y, Pixel pixel) {
        if (contains(x, y)) {
            int index = y * width + x;
            data[index] = pixel;
        } else {
            throw new IllegalArgumentException("Invalid coordinates");
        }
    }

    public Pixel pixel(int x, int y) {
        if (contains(x, y)) {
            int index = y * width + x;
            return data[index];
        }
        throw new IllegalArgumentException("Invalid coordinates");
    }
}
