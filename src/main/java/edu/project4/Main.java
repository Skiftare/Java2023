package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageNormalizer;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.Renderer;
import edu.project4.systeminteraction.FileAndPathManager;
import edu.project4.systeminteraction.ImageProperties;
import edu.project4.systeminteraction.ImagePropertiesParser;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ImagePropertiesParser propertiesParser = new ImagePropertiesParser(args);

        ImageProperties properties = propertiesParser.parse();
        Renderer render = new MultiThreadRender(properties);
        //Renderer render = new OneThreadRender();
        FileAndPathManager manager = new FileAndPathManager();
        ImageNormalizer norm = new ImageNormalizer();
        FractalImage image = render.makeImage();
        image = norm.gammaCorrect(image);
        manager.saveToFile(image,properties);

    }
}
