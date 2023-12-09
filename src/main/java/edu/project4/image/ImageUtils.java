package edu.project4.image;

import java.nio.file.Path;

public final class ImageUtils {
    public static final double X_MAX =  0.98722222222;
    public static final double X_MIN =  -0.98722222222;
    public static final double Y_MAX =  0.6;
    public static final double Y_MIN =  -0.6;
    public static final Path PATH_TO_OUTPUT_FOLDER = Path.of("src/main/resources");
    public static final int COUNT_OF_RANDOM_POINTS = 125;
    public static final int COUNT_OF_FRACTAL_POINTS = (int) 1e5;

}
