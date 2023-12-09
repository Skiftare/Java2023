package edu.project3.systeminteraction;

import edu.project3.tables.Table;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings("HideUtilityClassConstructor")
public class FileAndPathManager {
    private static final String FOLDER_FOR_OUTPUT = "src/main/resources/";
    private static String fileFormat = null;
    private static String fileName = null;

    public static void setFileName(String newFileName) {
        fileName = newFileName;
    }

    public static String getFileFormat() {
        return fileFormat;
    }

    public static void setFileFormat(String newFileFormat) {
        fileFormat = newFileFormat;
    }

    public static void makeFileName() {
        //IDE предлагала заменить StringBuilder на String.
        String baseName = DateFormatter.getCurrentDateAsString() + "_LogAnalyzer_output";
        String variantOfName = baseName;
        int count = 1;
        while (new File(FOLDER_FOR_OUTPUT
            + variantOfName
            + fileFormat)
            .exists()) {
            variantOfName = baseName + "_" + count;
            count++;
        }
        fileName = variantOfName;

    }

    public static void initFileForLogOutput() {
        //Does we always need to write in the same location, even re-write files?
        //I think not

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

    public static List<Path> findLogFiles(String logPath) throws IOException {
        List<Path> logFiles = new ArrayList<>();

        File file = new File(logPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles((dir, name) -> name.endsWith(".txt"));
            if (files != null) {
                for (File f : files) {
                    logFiles.add(Paths.get(f.getAbsolutePath()));
                }
            }
        } else if (file.isFile()) {
            logFiles.add(Paths.get(file.getAbsolutePath()));
        } else {
            logFiles = findFilesRecursively(logPath);
        }
        return logFiles;
    }

    private static List<Path> findFilesRecursively(String logPath) {
        List<Path> logFiles = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] folders = logPath.split("/");

        for (String subDir : folders) {
            if (Pattern.matches("[\\w.-]+", subDir)) {
                sb.append(subDir).append('/');
            } else {
                break;
            }
        }

        String baseDir = sb.toString();

        try (Stream<Path> stream = Files.walk(Paths.get(baseDir))) {

            List<File> subPaths = stream
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .toList();

            for (File subPath : subPaths) {
                if (subPath.toPath().getFileName().toString().equals(folders[folders.length - 1])) {
                    logFiles.add(subPath.toPath());
                }
            }

        } catch (IOException e) {
            ErrorLogger.createLogError(e.getMessage());
        }
        return logFiles;
    }

    public static BufferedReader createReaderForLogParser(Path logPath) throws IOException {
        return Files.newBufferedReader(logPath);
    }

    public static void writeToFile(Table tables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(
            FOLDER_FOR_OUTPUT
                + fileName
                + fileFormat,
            true
        ))) {
            writer.write(tables.printAsString());
        } catch (IOException e) {
            ErrorLogger.createLogError("Ошибка при записи файла: " + e.getMessage());
        }
    }

}
