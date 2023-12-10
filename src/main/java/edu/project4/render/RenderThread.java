package edu.project4.render;

import edu.project4.components.ImageProperties;
import edu.project4.components.Pixel;
import edu.project4.components.Point;
import edu.project4.image.FractalImage;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.afin.AfinCompose;
import edu.project4.transformation.afin.AfinTransformation;
import edu.project4.transformation.nonlinear.NonLinearCompose;
import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.image.ImageUtils.COUNT_OF_RANDOM_POINTS;
import static edu.project4.image.ImageUtils.X_MAX;
import static edu.project4.image.ImageUtils.X_MIN;
import static edu.project4.image.ImageUtils.Y_MAX;
import static edu.project4.image.ImageUtils.Y_MIN;
import static java.lang.Math.cos;
import static java.lang.Math.max;
import static java.lang.Math.sin;

class RenderThread implements Runnable {
    private static final int START_FRACTAL_VAL = -20;
    private final FractalImage image;
    private final ThreadLocalRandom random;
    private final int countOfPointsPerThread;
    private final AfinCompose compositionOfAffinity;
    private final int sym;
    private final NonLinearCompose vars;
    private final int countOfCores;

    RenderThread(
        FractalImage image,
        ImageProperties prop,
        AfinCompose compositionOfAffinity
    ) {
        this.image = image;
        this.random = ThreadLocalRandom.current();
        this.sym = prop.symmetry();
        this.vars = prop.vars();
        this.compositionOfAffinity = compositionOfAffinity;
        this.countOfPointsPerThread = prop.countOfPoints();
        this.countOfCores = prop.cores();

    }

    private FractalImage getImage() {
        return image;
    }

    private int getMedianVal(int a, int b) {
        return (a + b) >> 1;
    }

    private Color mixColors(Color pixel1, Color pixel2) {
        int red1 = pixel1.getRed();
        int green1 = pixel1.getGreen();
        int blue1 = pixel1.getBlue();

        int red2 = pixel2.getRed();
        int green2 = pixel2.getGreen();
        int blue2 = pixel2.getBlue();

        int newRed = getMedianVal(red1, red2);
        int newGreen = getMedianVal(green1, green2);
        int newBlue = getMedianVal(blue1, blue2);

        return new Color(newRed, newGreen, newBlue);
    }

    private Point normalizePoint(Point p) {

        double x = p.x();
        double y = p.y();
        x = image.width() - Math.ceil(((X_MAX * (1 - x)) * image.width() / (X_MAX - X_MIN)));
        y = image.height() - Math.ceil(((Y_MAX - y) * image.height() / (Y_MAX - Y_MIN)));
        return new Point(x, y);
    }

    private Boolean isInside(int xCoord, int yCoord) {
        return xCoord >= 0 && yCoord >= 0 && xCoord < image.width() && yCoord < image.height();
    }

    public void run() {
        double newX;
        double newY;
        int xCoord;
        int yCoord;
        int delta = countOfCores + max(sym, 1);
        for (int n = 0; n < COUNT_OF_RANDOM_POINTS; n += delta) {
            newX = X_MIN + (X_MAX - X_MIN) * random.nextDouble();
            newY = Y_MIN + (Y_MAX - Y_MIN) * random.nextDouble();
            for (int step = START_FRACTAL_VAL; step < countOfPointsPerThread; step += delta) {

                Transformation trans = vars.getNonLinear();

                AfinTransformation afin = compositionOfAffinity.getRandomAfin();

                Point p = trans.apply(afin.apply(new Point(newX, newY)));
                newX = p.x();
                newY = p.y();

                if (step >= 0) {
                    double theta = 0.0;
                    for (int s = 0; s < sym; s++) {
                        theta += 2 * Math.PI / (float) (sym);
                        double xFinCoord = newX * cos(theta) + newY * sin(theta);
                        double yFinCoord = newY * cos(theta) + newX * sin(theta);
                        Point finalPoint = new Point(xFinCoord, yFinCoord);
                        finalPoint = normalizePoint(finalPoint);
                        xCoord = (int) finalPoint.x();
                        yCoord = (int) finalPoint.y();

                        if (isInside(xCoord, yCoord)) {
                            synchronized (image.pixel(xCoord, yCoord)) {
                                Pixel curr = image.pixel(xCoord, yCoord);
                                if (curr.hitCount() == 0) {
                                    image.setPixel(xCoord, yCoord,
                                        new Pixel(afin.getColor(), 1)
                                    );
                                } else {

                                    image.setPixel(xCoord, yCoord,
                                        new Pixel(mixColors(afin.getColor(), curr.col()), curr.hitCount() + 1)
                                    );
                                }
                            }
                        }
                    }

                }

            }
        }

    }
}


