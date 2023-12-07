package edu.project4;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;


public class FileAndPathManager {
    void saveToFile(FractalImage image, Path pathToFile) {
        BufferedImage savedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        File outputFile = new File("output.png");
        for(int x = 0;x<image.width();x++){
            for(int y = 0;y<image.height();y++){
                Color col = image.pixel(x,y).col();

                savedImage.setRGB(x,y,image.pixel(x,y).col().getRGB());

            }
        }
        try {
            ImageIO.write(savedImage, "png", outputFile);
            System.out.println("Изображение успешно записано на диск.");
        } catch (IOException e) {
            System.out.println("Ошибка при записи изображения на диск: " + e.getMessage());
        }
    }
}
