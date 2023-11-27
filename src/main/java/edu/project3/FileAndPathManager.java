package edu.project3;

import edu.project3.utility.UtilityClass;
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

public class FileAndPathManager {
    public static String makeFileName() {
        return DateFormatter.getCurrentDateAsString() + "_LogAnalyzer_output";
    }

    public static void initFileForLogOutput() {
        //Does we always need to write in the same location, even re-write files?
        //I think not
        System.out.println(UtilityClass.getFileFormat());
        UtilityClass.setPathToOutputFile(
            UtilityClass.getFolderForOutput()
                + UtilityClass.getFileName()

                + UtilityClass.getFileFormat()
        );
        try {
            File file = new File(UtilityClass.getPathToOutputFile());
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

    static void writeToFile(Table tables) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UtilityClass.getPathToOutputFile(), true))) {
            writer.write(tables.printAsString());
        } catch (IOException e) {
            ErrorLogger.createLogError("Ошибка при записи файла: " + e.getMessage());
        }
    }

}
