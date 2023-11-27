package edu.project3;

import edu.project3.utility.UtilityClass;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("HideUtilityClassConstructor")
public class ArgumentsData {
    private static String path;

    private static Date from = null;
    private static Date to = null;
    private static String fromStr = null;
    private static String toStr = null;

    public static String getPath() {
        return path;
    }

    public static Date getFrom() {
        return from;
    }

    public static Date getTo() {
        return to;
    }

    public static String getFromStr() {
        return fromStr;
    }

    public static String getToStr() {
        return toStr;
    }

    public static void setPath(String path) {
        ArgumentsData.path = path;
    }

    public static void setFrom(Date from) {
        ArgumentsData.from = from;
    }

    public static void setTo(Date to) {
        ArgumentsData.to = to;
    }

    public static void setFromStr(String fromStr) {
        ArgumentsData.fromStr = fromStr;
    }

    public static void setToStr(String toStr) {
        ArgumentsData.toStr = toStr;
    }

    private static ArrayList<String> parseArgs(String[] args) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : args) {
            String[] words = str.split(" ");
            result.addAll(Arrays.asList(words));
        }
        return result;
    }

    // Processing function
    public static void processArgs(String[] args) {

        ArrayList<String> parsedArgs = parseArgs(args);

        for (int i = 0; i < parsedArgs.size(); i += 2) {

            switch (parsedArgs.get(i)) {
                case "--path":
                    setPath(parsedArgs.get(i + 1));
                    break;
                case "--from":
                    setFrom(DateFormatter.convertForParsingArguments(parsedArgs.get(i + 1)));
                    setFromStr(parsedArgs.get(i + 1));
                    break;
                case "--to":
                    setTo(DateFormatter.convertForParsingArguments(parsedArgs.get(i + 1)));
                    setToStr(parsedArgs.get(i + 1));
                    break;
                case "--format":
                    UtilityClass.setFileFormat(parsedArgs.get(i + 1));
                    break;
                default:
                    ErrorLogger.createLogError("неизветсный аргумент");
            }
        }
        if (path == null) {
            throw new RuntimeException("no path to parse logs");
        }
        assert UtilityClass.getFileFormat() != null;
        UtilityClass.setFileFormat(UtilityClass.getFileFormat().equals("markdown") ? ".md" : ".adoc");
        UtilityClass.setFileName(FileAndPathManager.makeFileName());
    }
}
