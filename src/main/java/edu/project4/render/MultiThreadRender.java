package edu.project4.render;

import edu.project4.components.ImageProperties;
import edu.project4.image.FractalImage;
import edu.project4.systeminteraction.ErrorLogger;
import edu.project4.transformation.afin.AfinCompose;
import java.security.SecureRandom;
import static edu.project4.image.ImageUtils.COUNT_OF_AFIN;

public class MultiThreadRender implements Renderer {


    private final ImageProperties prop;
    private final int customCountOfThreads;

    public MultiThreadRender(ImageProperties properties) {
        this.prop = properties;
        this.customCountOfThreads = properties.cores();
    }

    public FractalImage makeImage(){
        FractalImage image = FractalImage.create(prop.width(), prop.height());
        Thread[] threads = new Thread[customCountOfThreads];
        SecureRandom[] randoms = new SecureRandom[customCountOfThreads];
        AfinCompose afin = new AfinCompose(COUNT_OF_AFIN);

        for (int i = 0; i < customCountOfThreads; i++) {
            ErrorLogger.createLog("Thread " + i + " has been started");
            randoms[i] = new SecureRandom();
            threads[i] = new Thread(new RenderThread(image, randoms[i], prop, afin));
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
