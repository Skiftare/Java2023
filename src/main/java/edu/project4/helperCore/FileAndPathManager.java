package edu.project4.helperCore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileAndPathManager {
    private static String fileFormat = null;
    private static final String FOLDER_FOR_OUTPUT = "src/main/resources/";
    private static String fileName = null;

    public static void setFileFormat(String newFileFormat) {
        fileFormat = newFileFormat;
    }

    public static void setFileName(String newFileName) {
        fileName = newFileName;
    }

    public static String getFileFormat() {
        return fileFormat;
    }
    static String  getCurrentDateAsString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd_HH_mm-YY-MM");
        return dateFormat.format(new Date());
    }


    private static void makeFileName() {
        //IDE предлагала заменить StringBuilder на String.
        String baseName = getCurrentDateAsString() + "_fractalFlame";
        String variantOfName = baseName;
        int count = 1;
        while (new File(FOLDER_FOR_OUTPUT
            + variantOfName
            +"."
            + fileFormat)
            .exists()) {
            variantOfName = baseName + "_" + count;
            count++;
        }
        fileName = variantOfName;

    }

    public static void initFileForFlameOutput() {
        //Does we always need to write in the same location, even re-write files?
        //I think not
        makeFileName();

        try {
            File file = new File(FOLDER_FOR_OUTPUT + fileName + fileFormat);
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("");
                }
            } else {
                if (!file.createNewFile()) {
                    ErrorLogger.createLogError("Файл создать не удалось");
                }
            }

        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }







}
