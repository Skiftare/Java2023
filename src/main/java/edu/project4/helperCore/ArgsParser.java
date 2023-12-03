package edu.project4.helperCore;

import edu.project4.render.ImageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import static edu.project4.render.ImageFormat.JPEG;

public class ArgsParser {
    private static ArrayList<String> parseArgs(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : args) {
            String[] words = str.split(" ");
            result.addAll(Arrays.asList(words));
        }
        return result;
    }
    private ImageFormat parseImageFormat(String input) {
        return switch (input.toLowerCase()) {
            case "jpeg", "jpg" -> ImageFormat.JPEG;
            case "png" -> ImageFormat.PNG;
            case "bmp" -> ImageFormat.BMP;
            default -> ImageFormat.JPEG; // Возвращаем JPEG как значение по умолчанию
        };
    }
    public ArgsState parse(String[] args){
        ArrayList<String> parsedArgs = parseArgs(args);
        ArgsState argumentClass = new ArgsState(false,false, JPEG);
        boolean flagOfParameter;
        for (int i = 0; i < parsedArgs.size(); i += 2) {

            switch (parsedArgs.get(i)) {
                case "--symmetry":
                    flagOfParameter = (Objects.equals(parsedArgs.get(i + 1), "true"));
                    argumentClass.setSymmetryState(flagOfParameter);

                    break;
                case "--gamma":
                    flagOfParameter = (Objects.equals(parsedArgs.get(i + 1), "true"));
                    argumentClass.setGammaState(flagOfParameter);
                    break;
                case "--format":
                    String formatAsRowString = parsedArgs.get(i+1);
                    ImageFormat format = parseImageFormat(formatAsRowString);
                    argumentClass.setFormat(format);
                default:
                    ErrorLogger.createLogError("неизвестный аргумент");
            }
        }
        return argumentClass;
    }
}
