package edu.project4.render.multiThread;

import edu.project4.FractalImage;
import edu.project4.afin.AfinCompose;
import edu.project4.nonlinear.NonLinearCompose;
import edu.project4.render.Renderer;
import java.security.SecureRandom;

public class MultiThreadRender implements Renderer {
    private final int sym = 4;


    private final int imageWidth = 2160;
    private final int imageHeight = 1440;
    private final int countOfThreads = Runtime.getRuntime().availableProcessors();
    private final int countOfAffinityTransformations = 10;
    private final int countOfPoints = (int) 1e6;

    private final AfinCompose compositionOfAffinity;
    private final NonLinearCompose vars;

    public MultiThreadRender() {
        compositionOfAffinity = new AfinCompose(countOfAffinityTransformations);
        vars = new NonLinearCompose();
    }



    public FractalImage makeImage() {
        FractalImage image = FractalImage.create(imageWidth, imageHeight);
        Thread[] threads = new Thread[countOfThreads];
        SecureRandom[] randoms = new SecureRandom[countOfThreads];
        AfinCompose afin =  new AfinCompose(10);

        for (int i = 0; i < countOfThreads; i++) {
            randoms[i] = new SecureRandom();
            threads[i] = new Thread(new RenderThread(image, randoms[i], (int) 1e6, afin));
            threads[i].start();
        }

        for (int i = 0; i < countOfThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return image;
    }
}
