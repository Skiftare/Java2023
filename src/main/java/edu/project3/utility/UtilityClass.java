package edu.project3.utility;

@SuppressWarnings("HideUtilityClassConstructor")
public class UtilityClass {
    public static String fileFormat;
    public static String pathToOutputFile;
    public static final String FOLDER_FOR_OUTPUT = "src/main/resources/";
    public static String fileName = null;


    public final static String YEAR_MONTH_AND_DATE = "yyyy-MM-dd";
    public final static String HOURS_MINUTES_AND_SECONDS = "HH:mm:ss";
    public final static String HTTP_STRING = "http";
    public final static int BYTES_IN_KILOBYTE = 1024;
    public final static int VARIATIONS_OF_RESPONSE_CODES = 6;
    public final static int MULTIPLIER_FOR_CODES = 100;

    public static String getFileFormat() {
        return fileFormat;
    }

    public static void setFileFormat(String fileFormat) {
        UtilityClass.fileFormat = fileFormat;
    }

    public static String getPathToOutputFile() {
        return pathToOutputFile;
    }

    public static void setPathToOutputFile(String pathToOutputFile) {
        UtilityClass.pathToOutputFile = pathToOutputFile;
    }

    public static String getFolderForOutput() {
        return FOLDER_FOR_OUTPUT;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        UtilityClass.fileName = fileName;
    }


    public static String getYearMonthAndDate() {
        return YEAR_MONTH_AND_DATE;
    }

    public static String getHoursMinutesAndSeconds() {
        return HOURS_MINUTES_AND_SECONDS;
    }

    public static String getHttpString() {
        return HTTP_STRING;
    }

    public static int getBytesInKilobyte() {
        return BYTES_IN_KILOBYTE;
    }

    public static int getVariationsOfResponseCodes() {
        return VARIATIONS_OF_RESPONSE_CODES;
    }

    public static int getMultiplierForCodes() {
        return MULTIPLIER_FOR_CODES;
    }


}
