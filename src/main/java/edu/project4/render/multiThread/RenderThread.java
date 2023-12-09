package edu.project4.render.multiThread;

import edu.project4.FractalImage;
import edu.project4.Transformation;
import edu.project4.afin.AfinCompose;
import edu.project4.afin.AfinTransformation;
import edu.project4.components.Pixel;
import edu.project4.components.Point;
import edu.project4.nonlinear.HeartTransformation;
import edu.project4.nonlinear.NonLinearCompose;
import java.awt.Color;
import java.security.SecureRandom;
import java.util.ArrayList;
import static edu.project4.ImageUtils.X_MAX;
import static edu.project4.ImageUtils.X_MIN;
import static edu.project4.ImageUtils.Y_MAX;
import static edu.project4.ImageUtils.Y_MIN;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
 class RenderThread implements Runnable {
    private final FractalImage image;
    private final SecureRandom random;
    private final int imageWidth;
    private int imageHeight;
    private final int countOfPoints;
    private final AfinCompose compositionOfAffinity;

     private Color mixColors(Color pixel1, Color pixel2) {
         int red1 = pixel1.getRed();
         int green1 = pixel1.getGreen();
         int blue1 = pixel1.getBlue();

         int red2 = pixel2.getRed();
         int green2 = pixel2.getGreen();
         int blue2 = pixel2.getBlue();

         int newRed = (red1 + red2) / 2;
         int newGreen = (green1 + green2) / 2;
         int newBlue = (blue1 + blue2) / 2;

         return new Color(newRed, newGreen, newBlue);
     }

     private Point normalizePoint(Point p) {

         double x = p.x();
         double y = p.y();
         x = image.width() -Math.ceil((X_MAX-x)/(X_MAX-X_MIN)*image.width());
         y = image.height() - Math.ceil((Y_MAX-y)/(Y_MAX-Y_MIN)*image.height());
         return new Point(x, y);

     }

    public RenderThread(FractalImage image, SecureRandom random, int countOfPoints, AfinCompose compositionOfAffinity) {
        this.image = image;
        this.random = random;
        this.imageHeight = image.height();
        this.imageWidth = image.height();
        this.countOfPoints = countOfPoints;
        this.compositionOfAffinity = compositionOfAffinity;

    }

    public void run() {
        double newX = X_MIN + (X_MAX - X_MIN) * random.nextDouble();
        double newY = Y_MIN + (Y_MAX - Y_MIN) * random.nextDouble();
        ArrayList<Double> ys = new ArrayList<>();
        ArrayList<Double> xs = new ArrayList<>();
        NonLinearCompose vars = new NonLinearCompose();
        for (int step = -20; step < countOfPoints; step++) {
            newX = X_MIN + (X_MAX - X_MIN) * random.nextDouble();
            newY = Y_MIN + (Y_MAX - Y_MIN) * random.nextDouble();


            //Transformation sin = vars.getRangomNonLinear();
            Transformation sin = new HeartTransformation();
            AfinTransformation afin = compositionOfAffinity.getRandomAfin();
            Point p = afin.apply(new Point(newX, newY));
            Point pp = sin.apply(p);
            newX = pp.x();
            newY = pp.y();



            double theta = 0.0;
            int sym = 1;
            if (newX >= X_MIN && newX <= X_MAX && newY <= Y_MAX && newY>=Y_MIN) {
                int xCoord = (int) Math.round(newX);
                int yCoord = (int) Math.round(newY);
                xs.add(newX);
                ys.add(newY);
                for (int s = 0; s < sym; s++) {
                    theta += 2 * Math.PI / (float) (sym);
                    double xFinCoord = newX * cos(theta) + newY * sin(theta);
                    double yFinCoord = newY * cos(theta) + newX * sin(theta);
                    Point finalPoint = new Point(xFinCoord, yFinCoord);
                    finalPoint = normalizePoint(finalPoint);
                    xCoord = (int) finalPoint.x();
                    yCoord = (int) finalPoint.y();

                    synchronized(image) {
                        if (image.contains(xCoord, yCoord)) {
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
        System.out.println(xs.stream().min(Double::compare).get()+ " <-> "+xs.stream().max(Double::compare).get());
        System.out.println(ys.stream().min(Double::compare).get()+ " <-> "+ys.stream().max(Double::compare).get());
    }
}
