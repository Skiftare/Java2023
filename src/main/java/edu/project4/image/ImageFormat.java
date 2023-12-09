package edu.project4.image;

public enum ImageFormat {
    JPEG, BMP, PNG;

    @Override
    public String toString() {
        return switch (this) {
            case JPEG -> "jpeg";
            case BMP -> "bmp";
            case PNG -> "png";
        };
    }
}
