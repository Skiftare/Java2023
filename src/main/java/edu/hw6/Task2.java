package edu.hw6;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    private final static org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static String makeCopyName(Path path, String income) {
        String fileName = income;
        String baseName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = baseName + " — копия" + extension;

        int copyNumber = 1;
        while (Files.exists(path.resolveSibling(newFileName))) {
            copyNumber++;
            newFileName = baseName + " — копия (" + copyNumber + ")" + extension;
        }
        return newFileName;
    }

    public static void cloneFile(Path path) {

        String newFileName = makeCopyName(path, path.getFileName().toString());
        try (RandomAccessFile sourceFile = new RandomAccessFile(path.toFile(), "r");
             FileChannel sourceChannel = sourceFile.getChannel();
             FileChannel destinationChannel = FileChannel.open(path.resolveSibling(newFileName),
                 StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE
             )) {

            MappedByteBuffer buffer = sourceChannel.map(FileChannel.MapMode.READ_ONLY, 0, sourceChannel.size());
            destinationChannel.write(buffer);
            String buf = ("Копия файла успешно создана: " + newFileName);
            LOGGER.info(buf);
        } catch (IOException e) {
            String buf = ("Ошибка при создании копии файла: " + e.getMessage());
            LOGGER.info(buf);
        }
    }
}
