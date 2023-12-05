package edu.hw9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileSearchTaskTest {
    private static final String DIRECTORY_PATH = "src\\test\\java\\edu\\hw9\\testFolder";


    @BeforeEach
    public void setUp() {
        // Создаем файлы и папки для тестирования
        createFilesAndDirectories();
    }

    @AfterEach
    public void tearDown() {
        // Удаляем файлы и папки после тестирования
        deleteFilesAndDirectories();
    }

    @Test
    @DisplayName("Случай использования поиска для папок с > n файлов внутри")
    public void testThatGetSearchFolderAndMinimumFilesNumbersAndReturnedAllFoldersThatSatisfy() {
        //given: number of files & directory
        int minNumberOfFiles = 2;

        //when: we make search in the directories with predicate help
        Predicate<File> predicate = file -> file.isDirectory() && Objects.requireNonNull(file.listFiles()).length > minNumberOfFiles;
        FileSearchTask directorySearchTask = new FileSearchTask(
            new File(DIRECTORY_PATH),
            predicate,
            true
        );

        //then: we get only one good directory. As we axpected
        try (ForkJoinPool pool = new ForkJoinPool()) {
            List<File> files = pool.invoke(directorySearchTask);
            Path expected = Path.of(DIRECTORY_PATH + File.separator + "dir1");

            assertEquals(1, files.size());
            assertEquals(expected, files.get(0).getPath());
        } catch (Exception e){
            ErrorLogger.createLogError(e.getMessage());
        }
    }

    @Test
    @DisplayName("Случай использования поиска для файлов с заданным расширением")
    public void testThatGetSearchFolderAndExtensionAndReturnedAllFilesThatSatisfy() {
        //given: extension of file & search folder
        File directory = new File(DIRECTORY_PATH);
        String extension = ".txt";

        //when: we make search in the directories with predicate help
        Predicate<File> predicate = file -> file.isFile() && file.getName().endsWith(extension);
        FileSearchTask task = new FileSearchTask(
            directory,
            predicate,
            false);

        //then: we get number of files.
        try (ForkJoinPool pool = new ForkJoinPool()) {
            List<File> files = pool.invoke(task);
            int expectedSize = 4;
            Path expectedPath = Path.of(DIRECTORY_PATH + File.separator + "file4.txt");

            assertEquals(expectedSize, files.size());
            assertEquals(expectedPath, files.get(4).getPath());
        }
        catch (Exception e){
            ErrorLogger.createLogError(e.getMessage());
        }
    }

    private void createFilesAndDirectories() {

            new File(DIRECTORY_PATH).mkdir();

            try {
                File file1 = new File(DIRECTORY_PATH + File.separator + "file1.txt");
                file1.createNewFile();

                File file2 = new File(DIRECTORY_PATH + File.separator + "dir1" + File.separator + "file2.txt");
                file2.getParentFile().mkdirs();
                file2.createNewFile();

                File file3 = new File(DIRECTORY_PATH + File.separator + "dir1" + File.separator + "file3.txt");
                file3.createNewFile();

                File file4 = new File(DIRECTORY_PATH + File.separator + "dir1" + File.separator + "file4.txt");
                file4.createNewFile();

            } catch (IOException e) {
                ErrorLogger.createLogError(e.getMessage());

            }

    }

    private void deleteFilesAndDirectories() {
        File directory = new File(DIRECTORY_PATH);
        deleteFilesAndDirectoriesRecursive(directory);
        directory.delete();
    }

    private void deleteFilesAndDirectoriesRecursive(File file) {
        if (file.isDirectory()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                deleteFilesAndDirectoriesRecursive(subFile);
            }
        }

        file.delete();
    }
}
