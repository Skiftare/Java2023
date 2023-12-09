package edu.project4.render;

import edu.project4.image.FractalImage;
import edu.project4.systeminteraction.ErrorLogger;
import edu.project4.systeminteraction.ImageProperties;
import edu.project4.transformation.afin.AfinCompose;
import edu.project4.transformation.nonlinear.NonLinearCompose;
import java.security.SecureRandom;

public class MultiThreadRender implements Renderer {
    private final int sym;


    private final int imageWidth;
    private final int imageHeight;
    private final int countOfThreads = Runtime.getRuntime().availableProcessors();
    private final int countOfAffinityTransformations = 10;

    private final AfinCompose compositionOfAffinity;
    private final NonLinearCompose vars;

    public MultiThreadRender(ImageProperties properties) {
        compositionOfAffinity = new AfinCompose(countOfAffinityTransformations);
        imageHeight = properties.height();
        imageWidth = properties.width();
        vars = new NonLinearCompose();
        sym = properties.symmetry();
    }



    public FractalImage makeImage() {
        FractalImage image = FractalImage.create(imageWidth, imageHeight);
        Thread[] threads = new Thread[countOfThreads];
        SecureRandom[] randoms = new SecureRandom[countOfThreads];
        AfinCompose afin =  new AfinCompose(10);

        for (int i = 0; i < countOfThreads; i++) {
            randoms[i] = new SecureRandom();
            threads[i] = new Thread(new RenderThread(image, randoms[i], sym, afin));
            threads[i].start();
        }

        for (int i = 0; i < countOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }

        return image;
    }
}
