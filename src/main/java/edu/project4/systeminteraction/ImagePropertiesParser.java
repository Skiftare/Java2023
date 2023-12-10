package edu.project4.systeminteraction;

import edu.project4.components.ImageProperties;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.nonlinear.NonLinearCompose;
import edu.project4.transformation.nonlinear.variations.DiskTransformation;
import edu.project4.transformation.nonlinear.variations.HeartTransformation;
import edu.project4.transformation.nonlinear.variations.PolarTransformation;
import edu.project4.transformation.nonlinear.variations.SinusoidalTransformation;
import edu.project4.transformation.nonlinear.variations.SphericalTransformation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Properties;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_COUNT_OF_POINTS;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_HEIGHT;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_IMAGE_FORMAT;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_NONLINEAR;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_OUTPUT_FOLDER;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_PROPERTIES_FOLDER;
import static edu.project4.systeminteraction.SystemUtils.DEFAULT_WIDTH;
import static edu.project4.systeminteraction.SystemUtils.LOWER_BOUND_OF_SYMMETRY;
import static edu.project4.systeminteraction.SystemUtils.PROPERTIES_FILENAME;
import static edu.project4.systeminteraction.SystemUtils.UPPER_BOUND_OF_SYMMETRY;

public class ImagePropertiesParser {
    private final static int AVAILABLE_CORES = Runtime.getRuntime().availableProcessors();
    private static String propertiesPath = null;
    private final FileAndPathManager manager = new FileAndPathManager();

    public ImagePropertiesParser(String[] args) {
        StringBuilder pathPropertiesBuilder = new StringBuilder();
        if (args.length == 0 || !isCorrectPath(args[0])) {
            pathPropertiesBuilder.append(DEFAULT_PROPERTIES_FOLDER);
        } else {
            pathPropertiesBuilder.append(args[0]);
        }
        pathPropertiesBuilder.append(PROPERTIES_FILENAME);

        propertiesPath = pathPropertiesBuilder.toString();

    }

    private Boolean isCorrectPath(String path) {
        File folder = new File(path);
        return (folder.exists() && folder.isDirectory());
    }

    private NonLinearCompose getNonLinearFromProperty(String incomeS) {
        HashSet<Transformation> transSet = new HashSet<>();
        String s = incomeS.replace(" ", "");
        String[] splitedString = s.split(",");
        if (splitedString.length == 0) {
            splitedString = new String[1];
            splitedString[0] = DEFAULT_NONLINEAR;
        }
        boolean bFlagOfChoosedAnyOneRandom = false;
        for (int i = 0; i < splitedString.length && !bFlagOfChoosedAnyOneRandom; i++) {
            String transformation = splitedString[i];
            switch (transformation) {
                case "disk":
                    transSet.add(new DiskTransformation());
                    break;
                case "heart":
                    transSet.add(new HeartTransformation());
                    break;
                case "polar":
                    transSet.add(new PolarTransformation());
                    break;
                case "sinus":
                    transSet.add(new SinusoidalTransformation());
                    break;
                case "sphere":
                    transSet.add(new SphericalTransformation());
                    break;
                case "anyone":
                    transSet.clear();
                    transSet.add(new DiskTransformation());
                    transSet.add(new HeartTransformation());
                    transSet.add(new PolarTransformation());
                    transSet.add(new SinusoidalTransformation());
                    transSet.add(new SphericalTransformation());
                    bFlagOfChoosedAnyOneRandom = true;
                    break;
                default:
                    transSet.add(new DiskTransformation());
                    transSet.add(new HeartTransformation());
                    transSet.add(new PolarTransformation());
                    transSet.add(new SinusoidalTransformation());
                    transSet.add(new SphericalTransformation());
                    break;
            }
        }
        NonLinearCompose comp = new NonLinearCompose(transSet.toArray(new Transformation[0]));
        if (bFlagOfChoosedAnyOneRandom) {
            transSet.clear();
            transSet.add(comp.getNonLinear());
            comp = new NonLinearCompose(transSet.toArray(new Transformation[0]));

        }
        return comp;

    }

    private Boolean isPropertiesExist() {
        File file = new File(propertiesPath);
        if (!file.exists()) {
            ErrorLogger.createLogError("The properties file was not found.");
            return false;
        }
        return true;
    }

    private ImageProperties generateCustomProperties() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(propertiesPath);
        SecureRandom random = new SecureRandom();
        int symRand = random.nextInt(UPPER_BOUND_OF_SYMMETRY - LOWER_BOUND_OF_SYMMETRY + 1) + LOWER_BOUND_OF_SYMMETRY;

        Properties properties = new Properties();
        properties.load(fileInputStream);
        String fileName = properties.getProperty("fileName", manager.createFileName());
        String outputFolder = properties.getProperty("outputFolder", DEFAULT_OUTPUT_FOLDER);
        String fileExtension = properties.getProperty("fileExtension", DEFAULT_IMAGE_FORMAT.toString());
        int width = Integer.parseInt(properties.getProperty("width", String.valueOf(DEFAULT_WIDTH)));
        int height = Integer.parseInt(properties.getProperty("height", String.valueOf(DEFAULT_HEIGHT)));
        int sym = Integer.parseInt(properties.getProperty("symmetry", String.valueOf(symRand)));
        NonLinearCompose res = getNonLinearFromProperty(properties.getProperty("transformation", DEFAULT_NONLINEAR));
        int countOfPoints =
            (int) Double.parseDouble(properties.getProperty("points", String.valueOf(DEFAULT_COUNT_OF_POINTS)));
        int countOfCores = Integer.parseInt(properties.getProperty("cores", String.valueOf(AVAILABLE_CORES)));
        return new ImageProperties(
            fileName,
            outputFolder,
            fileExtension,
            width,
            height,
            sym,
            res,
            countOfPoints,
            countOfCores
        );
    }

    private ImageProperties generateDefaultProperties() {
        SecureRandom random = new SecureRandom();
        int sym = random.nextInt(UPPER_BOUND_OF_SYMMETRY - LOWER_BOUND_OF_SYMMETRY + 1) + LOWER_BOUND_OF_SYMMETRY;

        return new ImageProperties(
            manager.createFileName(),
            DEFAULT_OUTPUT_FOLDER,
            DEFAULT_IMAGE_FORMAT.toString(),
            DEFAULT_WIDTH,
            DEFAULT_HEIGHT,
            sym,
            getNonLinearFromProperty(DEFAULT_NONLINEAR),
            DEFAULT_COUNT_OF_POINTS,
            AVAILABLE_CORES
        );
    }

    public ImageProperties parse() throws IOException {
        ImageProperties props;
        if (!isPropertiesExist()) {
            props = generateDefaultProperties();
        } else {
            props = generateCustomProperties();
        }
        return props;
    }
}
