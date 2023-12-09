package edu.project4.image;

public enum ImageFormat {
    JPEG, BMP, PNG;

    @Override
    public String toString() {
        switch(this) {
            case JPEG:
                return "jpeg";
            case BMP:
                return "bmp";
            case PNG:
                return "png";
            default:
                throw new IllegalArgumentException("Unknown image format: " + this);
        }
    }
}
