package edu.project4;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args){
        OneThreadRender render = new OneThreadRender();
        FractalImage image = render.makeImage();
        FileAndPathManager manager = new FileAndPathManager();
        manager.saveToFile(image, Path.of("src/main/resources"));
    }
}
