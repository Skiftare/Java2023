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


    public FractalImage makeImage(){
        FractalImage image = new FractalImage(prop.width(), prop.height());
        Thread[] threads = new Thread[customCountOfThreads];
        AfinCompose afin = new AfinCompose(COUNT_OF_AFIN);
        FractalImage[] threadImages = new FractalImage[customCountOfThreads];

        for (int i = 0; i < customCountOfThreads; i++) {
            ErrorLogger.createLog("Thread " + i + " has been started");
            threads[i] = new Thread(new RenderThread(image, prop, afin));
            threads[i].start();

        }

        for (int i = 0; i < customCountOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }


        return image;
    }
}
