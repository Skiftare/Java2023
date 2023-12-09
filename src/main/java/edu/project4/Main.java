package edu.project4;

import edu.project4.render.Renderer;
import edu.project4.render.multiThread.MultiThreadRender;
import edu.project4.render.normalizer.ImageNormalizer;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        Renderer render = new MultiThreadRender();
        FractalImage image = render.makeImage();
        FileAndPathManager manager = new FileAndPathManager();
        ImageNormalizer norm = new ImageNormalizer();
        image = norm.gammaCorrect(image);
        manager.saveToFile(image, Path.of("src/main/resources"));
    }
}
