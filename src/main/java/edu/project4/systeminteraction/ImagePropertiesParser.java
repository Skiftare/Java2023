package edu.project4.systeminteraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Properties;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_HEIGHT;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_IMAGE_FORMAT;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_OPTIMIZATION;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_OUTPUT_FOLDER;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_PROPERTIES_FOLDER;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_WIDTH;
import static edu.project4.systeminteraction.SystemUtils.LOWER_BOUND_OF_SYMMETRY;
import static edu.project4.systeminteraction.SystemUtils.PROPERTIES_FILENAME;
import static edu.project4.systeminteraction.SystemUtils.UPPER_BOUND_OF_SYMMETRY;

public class ImagePropertiesParser {
    private static String propertiesPath = null;
    private FileAndPathManager manager = new FileAndPathManager();

    private Boolean isCorrectPath(String path) {
        File folder = new File(path+PROPERTIES_FILENAME);
        return (folder.exists() && folder.isDirectory());
    }

    private Boolean isPropertiesExist(){
        File file = new File(propertiesPath);
        if(!file.exists()){
            ErrorLogger.createLogError("The properties file was not found.");
            return false;
        }
        return true;
    }
    private ImageProperties generateCustomProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(propertiesPath);
        SecureRandom random = new SecureRandom();
        int symRand = random.nextInt(UPPER_BOUND_OF_SYMMETRY -LOWER_BOUND_OF_SYMMETRY+1)+LOWER_BOUND_OF_SYMMETRY;

        Properties properties = new Properties();
        properties.load(fileInputStream);

        String fileName = properties.getProperty("fileName", manager.createFileName());
        String outputFolder = properties.getProperty("outputFolder", DEFAULT_OUTPUT_FOLDER);
        String fileExtension = properties.getProperty("fileExtension", DEFAULT_IMAGE_FORMAT.toString());
        int width = Integer.parseInt(properties.getProperty("width", String.valueOf(DEFAULT_WIDTH)));
        int height = Integer.parseInt(properties.getProperty("height", String.valueOf(DEFAULT_HEIGHT)));
        int sym = Integer.parseInt(properties.getProperty("symmetry", String.valueOf(symRand)));
        boolean optimize = Boolean.parseBoolean(properties.getProperty("optimize", String.valueOf(DEFAULT_OPTIMIZATION)));

        return new ImageProperties(fileName, outputFolder, fileExtension, width, height, optimize, sym);
    }
    private ImageProperties generateDefaultProperties(){
        SecureRandom random = new SecureRandom();
        int sym = random.nextInt(UPPER_BOUND_OF_SYMMETRY -LOWER_BOUND_OF_SYMMETRY+1)+LOWER_BOUND_OF_SYMMETRY;

        ImageProperties props = new ImageProperties(
            manager.createFileName(),
            DEFAULT_OUTPUT_FOLDER,
            DEFAULT_IMAGE_FORMAT.toString(),
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT,
            DEFAULT_OPTIMIZATION,
            sym
        );
        return props;
    }

    public ImageProperties parse() throws IOException {
    ImageProperties props;
        if(!isPropertiesExist()){
            props = generateDefaultProperties();
        }
        else {
            props = generateCustomProperties();
        }
        return props;
    }
    public ImagePropertiesParser(String[] args){
        StringBuilder pathPropertiesBuilder = new StringBuilder();
        if(args.length == 0 || !isCorrectPath(args[0])){
            pathPropertiesBuilder.append(DEFAULT_PROPERTIES_FOLDER);
        }
        else{
            pathPropertiesBuilder.append(args[0]);
        }
        pathPropertiesBuilder.append(PROPERTIES_FILENAME);

        this.propertiesPath = pathPropertiesBuilder.toString();

    }
}
