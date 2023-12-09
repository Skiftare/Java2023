package edu.project4.render;

import edu.project4.image.FractalImage;
import edu.project4.systeminteraction.ErrorLogger;
import edu.project4.systeminteraction.ImageProperties;
import edu.project4.transformation.afin.AfinCompose;
import java.security.SecureRandom;
import static edu.project4.image.ImageUtils.COUNT_OF_AFIN;
import static java.lang.Math.max;

public class MultiThreadRender implements Renderer {

    private final int countOfThreads = max(1, Runtime.getRuntime().availableProcessors() - 1);
    private final ImageProperties prop;

    public MultiThreadRender(ImageProperties properties) {
        prop = properties;
    }

    public FractalImage makeImage() {
        FractalImage image = FractalImage.create(prop.width(), prop.height());
        Thread[] threads = new Thread[countOfThreads];
        SecureRandom[] randoms = new SecureRandom[countOfThreads];
        AfinCompose afin = new AfinCompose(COUNT_OF_AFIN);

        for (int i = 0; i < countOfThreads; i++) {
            ErrorLogger.createLog("Thread " + i + " has been started");
            randoms[i] = new SecureRandom();
            threads[i] = new Thread(new RenderThread(image, randoms[i], prop, afin));
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
