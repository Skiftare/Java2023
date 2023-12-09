package edu.project4.render.oneThread;

import edu.project4.FractalImage;
import edu.project4.Transformation;
import edu.project4.afin.AfinCompose;
import edu.project4.afin.AfinTransformation;
import edu.project4.components.Pixel;
import edu.project4.components.Point;
import edu.project4.nonlinear.HeartTransformation;
import edu.project4.nonlinear.NonLinearCompose;
import edu.project4.render.Renderer;
import java.awt.Color;
import java.security.SecureRandom;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class OneThreadRender implements Renderer {
    double XMIN = -1.7777;
    double XMAX = 1.7777;
    double YMIN = -1.8;
    int sym = 1;
    double YMAX = 1.8;
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

        Color blendedColor = new Color(newRed, newGreen, newBlue);
        return blendedColor;
    }

    private Point normalizePoint(Point p, int width, int height) {
        double x = p.x();
        double y = p.y();
        x = width -Math.ceil((XMAX-x)/(XMAX-XMIN)*width);
        y = height - Math.ceil((YMAX-y)/(YMAX-YMIN)*height);
        return new Point(x, y);

    }

    public FractalImage makeImage() {
        FractalImage image = FractalImage.create(2160, 1440);
        int countOfAffinityTransformations = 10;
        int countOfPoints = (int) 1e7;
        AfinCompose compositionOfAffinity = new AfinCompose(countOfAffinityTransformations);




        SecureRandom random = new SecureRandom();
        NonLinearCompose vars = new NonLinearCompose();

        double newX = XMIN + (XMAX - XMIN) * random.nextDouble();
        double newY = YMIN + (YMAX - YMIN) * random.nextDouble();

        for (int step = -20; step < countOfPoints; step++) {
            newX = XMIN + (XMAX - XMIN) * random.nextDouble();
            newY = YMIN + (YMAX - YMIN) * random.nextDouble();
            AfinTransformation afin = compositionOfAffinity.getRandomAfin();
            //Transformation sin = vars.getRangomNonLinear();
            Transformation sin = new HeartTransformation();
            Point p = afin.apply(new Point(newX, newY));
            Point pp = sin.apply(p);
            newX = pp.x();
            newY = pp.y();
            //pp = normalizePoint(pp, image.width(), image.height());
            newX = pp.x();
            newY = pp.y();
            double theta = 0.0;

            if (step >= 0 && newX >= XMIN && newX <= XMAX && newY>=YMIN&& newY<=YMAX) {
                int xCoord = (int) Math.round(newX);
                int yCoord = (int) Math.round(newY);
                for (int s = 0; s < sym; s++) {
                    theta += 2 * Math.PI / (float) (sym);
                    double xFinCoord = newX*cos(theta)+newY*sin(theta);
                    double yFinCoord = newY*cos(theta)+newX*sin(theta);
                    Point finalPoint = new Point(xFinCoord,yFinCoord);
                    finalPoint = normalizePoint(finalPoint, image.width(), image.height());
                    xCoord = (int) finalPoint.x();
                    yCoord = (int) finalPoint.y();
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
        return image;
    }

}
