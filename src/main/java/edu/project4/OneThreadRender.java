package edu.project4;

import edu.project4.afin.AfinCompose;
import edu.project4.afin.AfinTransformation;
import edu.project4.components.Pixel;
import edu.project4.components.Point;
import edu.project4.nonlinear.SinusoidalTransformation;
import java.awt.Color;
import java.security.SecureRandom;

public class OneThreadRender implements Renderer {

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

    public FractalImage makeImage() {
        FractalImage image = FractalImage.create(1920, 1080);
        int countOfAffinityTransformations = 5;
        int countOfPoints = (int) 1e5;
        AfinCompose compositionOfAffinity = new AfinCompose(countOfAffinityTransformations);

        double XMIN = -1.777;
        double XMAX = 1.777;
        double YMIN = -1;
        double YMAX = 1;

        SecureRandom random = new SecureRandom();
        SinusoidalTransformation sin = new SinusoidalTransformation();

        double newX = XMIN + (XMAX - XMIN) * random.nextDouble();
        double newY = YMIN + (YMAX - YMIN) * random.nextDouble();
        for (int step = -20; step < countOfPoints; step++) {
            AfinTransformation afin = compositionOfAffinity.getRandomAfin();
            Point p = afin.apply(new Point(newX, newY));
            Point pp = sin.apply(p);
            newX = pp.x();
            newY = pp.y();

            if (step >= 0) {
                int xCoord = (int) Math.round(newX);
                int yCoord = (int) Math.round(newY);

                if (image.contains(xCoord, yCoord)) {
                    Pixel curr = image.pixel(xCoord, yCoord);
                    if (curr.hitCount() == 0) {
                        image.setPixel(xCoord, yCoord,
                            new Pixel(afin.getColor(), 1)
                        );
                    } else {
                        image.setPixel(xCoord, yCoord,
                            new Pixel(afin.getColor(), curr.hitCount()+1));
                    }
                }

            }
        }
        return image;
    }

}