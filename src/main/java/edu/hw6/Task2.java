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
    private final static Character END_OF_FILENAME_CHAR = '.';
    private static final String COPY = " - копия";
    private static final String SUCCESS_COPY_MESSAGE = "Копия файла успешно создана: ";
    private static final String FAILURE_COPY_MESSAGE = "Ошибка при создании копии файла: ";

    static String createNameByNumber(String nameOfFile, Integer num, String extension) {
        StringBuilder copyName = new StringBuilder();
        copyName.append(nameOfFile);
        copyName.append(COPY);
        if (num == 1) {
            copyName.append(extension);
        } else {
            copyName.append(" (");
            copyName.append(num);
            copyName.append(")");
            copyName.append(extension);
        }
        return copyName.toString();
    }

    public static String makeCopyName(Path path, String income) {
        String baseName = income.substring(0, income.lastIndexOf(END_OF_FILENAME_CHAR));
        String extension = income.substring(income.lastIndexOf(END_OF_FILENAME_CHAR));
        int copyNumber = 1;
        String newFileName = createNameByNumber(baseName, copyNumber, extension);

        while (Files.exists(path.resolveSibling(newFileName))) {
            copyNumber++;
            newFileName = createNameByNumber(baseName, copyNumber, extension);
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
            if (destinationChannel.write(buffer) == 0) {
                LOGGER.info(FAILURE_COPY_MESSAGE);
            }
            String buf = (SUCCESS_COPY_MESSAGE + newFileName);
            LOGGER.info(buf);
        } catch (IOException e) {
            String buf = (FAILURE_COPY_MESSAGE + e.getMessage());
            LOGGER.info(buf);
        }
    }
}
