package edu.project4.render;

import edu.project4.components.ImageProperties;
import edu.project4.components.Pixel;
import edu.project4.image.FractalImage;
import edu.project4.systeminteraction.ErrorLogger;
import edu.project4.transformation.afin.AfinCompose;
import java.awt.Color;
import java.security.SecureRandom;
import static edu.project4.image.ImageUtils.COUNT_OF_AFIN;

public class MultiThreadRender implements Renderer {


    private final ImageProperties prop;
    private final int customCountOfThreads;

    public MultiThreadRender(ImageProperties properties) {
        this.prop = properties;
        this.customCountOfThreads = properties.cores();
    }
    private Color mixColors(Color pixel1, Color pixel2) {
        int red1 = pixel1.getRed();
        int green1 = pixel1.getGreen();
        int blue1 = pixel1.getBlue();

        int red2 = pixel2.getRed();
        int green2 = pixel2.getGreen();
        int blue2 = pixel2.getBlue();

        int newRed = (red1+red2)<<1;
        int newGreen =  (green1+green2)<<1;
        int newBlue =  (blue1+blue2)<<1;

        return new Color(newRed, newGreen, newBlue);
    }
    private FractalImage mergeImages(FractalImage image, FractalImage im){
        for(int i = 0;i<im.width();i++) {
            for (int j = 0; j < im.height(); j++) {
                Pixel curr = im.pixel(i, j);
                Pixel origin = image.pixel(i, j);
                if (origin.hitCount() == 0) {
                    image.setPixel(i, j, curr);
                } else {
                    image.setPixel(i,
                        j,
                        new Pixel(mixColors(origin.col(), curr.col()), curr.hitCount() + origin.hitCount()));
                }
            }
        }
        return image;
    }

    public FractalImage makeImage(){
        FractalImage image = FractalImage.create(prop.width(), prop.height());
        Thread[] threads = new Thread[customCountOfThreads];
        AfinCompose afin = new AfinCompose(COUNT_OF_AFIN);
        FractalImage[] threadImages = new FractalImage[customCountOfThreads];

        for (int i = 0; i < customCountOfThreads; i++) {
            threadImages[i] = FractalImage.create(image.width(), image.height());
            ErrorLogger.createLog("Thread " + i + " has been started");
            threads[i] = new Thread(new RenderThread(threadImages[i], prop, afin));
            threads[i].start();

        }

        for (int i = 0; i < customCountOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }
        for(int i = 1;i<customCountOfThreads;i++){
            threadImages[0] = mergeImages(threadImages[0],threadImages[1]);
        }
        return threadImages[0];

    }
}
