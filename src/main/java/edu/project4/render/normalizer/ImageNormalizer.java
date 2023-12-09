package edu.project4.render.normalizer;

import edu.project4.FractalImage;
import edu.project4.components.Pixel;
import java.awt.Color;
import static java.lang.Math.log10;
import static java.lang.Math.max;
import static java.lang.Math.pow;

public class ImageNormalizer {
    private static final double GAMMA_DEFAULT = 2.2;
    public FractalImage gammaCorrect(FractalImage image, double gamma){
        int width = image.width();
        int height = image.height();
        double maxCounts = 0;
        for(int xPos = 0;xPos<width;xPos++){
            for(int yPos = 0;yPos<height;yPos++){
                    maxCounts = max(maxCounts, image.pixel(xPos,yPos).hitCount());
            }
        }
        maxCounts = log10(maxCounts);
        for(int xPos = 0;xPos<width;xPos++){
            for(int yPos = 0;yPos<height;yPos++){
                double normal = log10(image.pixel(xPos,yPos).hitCount());
                normal/=maxCounts;
                Pixel curr = image.pixel(xPos,yPos);
                int r = (int) (curr.col().getRed()*pow(normal,1.0/gamma));
                int g = (int) (curr.col().getGreen()*pow(normal,1.0/gamma));
                int b = (int) (curr.col().getBlue()*pow(normal,1.0/gamma));
                Color normalizedCol= new Color(r,g,b);
                Pixel normalizedPixel = new Pixel(normalizedCol,image.pixel(xPos,yPos).hitCount());
                image.setPixel(xPos,yPos,normalizedPixel);
            }
        }
        return image;

    }


    public FractalImage gammaCorrect(FractalImage image){
        return gammaCorrect(image, GAMMA_DEFAULT);
    }


}
