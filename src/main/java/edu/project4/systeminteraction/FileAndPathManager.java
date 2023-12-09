package edu.project4.systeminteraction;

import edu.project4.image.FractalImage;
import edu.project4.image.ImageFormat;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static edu.project4.image.ImageFormat.BMP;
import static edu.project4.image.ImageFormat.JPEG;
import static edu.project4.image.ImageFormat.PNG;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_OUTPUT_FOLDER;

public class FileAndPathManager {
    public void saveToFile(FractalImage image, ImageProperties props) {
        BufferedImage savedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        File outputFile = new File(props.outputFolder()+'/'+props.fileName()+'.'+props.fileExtension());

        for(int x = 0; x < image.width(); x++) {
            for(int y = 0; y < image.height(); y++) {
                Color pixelColor = image.pixel(x, y).col();

                float[] hdrComponents = pixelColor.getRGBColorComponents(null);

                float red = hdrComponents[0];
                float green = hdrComponents[1];
                float blue = hdrComponents[2];

                float hdrBrightness = (red + green + blue) / 3.0f;

                float hdrSaturation = Math.max(Math.max(red, green), blue);

                Color hdrPixel = Color.getHSBColor(hdrBrightness, hdrSaturation, 1.0f);

                savedImage.setRGB(x, y, hdrPixel.getRGB());
            }
        }

        try {
            ErrorLogger.createLog(props.fileExtension());
            ImageIO.write(savedImage, props.fileExtension(), outputFile);
            ErrorLogger.createLog("Изображение успешно записано на диск.");
        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }



    public String createFileName() {
        String baseName = DateFormatter.getCurrentDateAsString() + "_Fractal_output";
        String variantOfName = baseName;
        int count = 1;
        boolean existAtLeastOne = false;
        for(ImageFormat format: new ImageFormat[]{PNG, BMP, JPEG}) {
            existAtLeastOne |= new File(DEFAULT_OUTPUT_FOLDER
                + variantOfName + "."
                + format).exists();
        }
        while (existAtLeastOne){
            existAtLeastOne = false;

            for(ImageFormat format: new ImageFormat[]{PNG, BMP, JPEG}) {
                existAtLeastOne |= new File(DEFAULT_OUTPUT_FOLDER
                    + variantOfName + "."
                    + format).exists();
            }

            variantOfName = baseName + "_" + count;
            count++;
        }
        return variantOfName;
    }
}
