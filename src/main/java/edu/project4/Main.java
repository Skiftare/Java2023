package edu.project4;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageNormalizer;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.Renderer;
import edu.project4.systeminteraction.ErrorLogger;
import edu.project4.systeminteraction.FileAndPathManager;
import edu.project4.systeminteraction.ImageProperties;
import edu.project4.systeminteraction.ImagePropertiesParser;
import java.io.IOException;

@SuppressWarnings("HideUtilityClassConstructor")
public class Main {

    public static void main(String[] args) throws IOException {
        ImagePropertiesParser propertiesParser = new ImagePropertiesParser(args);
        ErrorLogger.createLog("Args were parsed");
        ImageProperties properties = propertiesParser.parse();
        ErrorLogger.createLog("ImageProperties created");

        Renderer render = new MultiThreadRender(properties);
        ErrorLogger.createLog("Render init ended");
        FileAndPathManager manager = new FileAndPathManager();
        ImageNormalizer norm = new ImageNormalizer();
        FractalImage image = render.makeImage();
        ErrorLogger.createLog("Image generated");
        image = norm.gammaCorrect(image);
        ErrorLogger.createLog("Image corrected");
        manager.saveToFile(image, properties);

    }
}
