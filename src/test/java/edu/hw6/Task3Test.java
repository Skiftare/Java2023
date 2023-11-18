package edu.hw6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.magicNumber;
import static edu.hw6.AbstractFilter.smallerThan;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task3Test {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;
    String FILE_PATH = "src/test/java/edu/hw6/resources/";
    String FILE_NAME = "Рисунок1.png";

    @Test
    @DisplayName("Тест на поиск файла по заданным фильтрам")
    void testThatGetPathWithFilterAndReturnedFile() throws IOException {
        //Given: path to folder, where we need search file

        //When: create parameters for needed file
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(smallerThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"));
        //Then: we get 1 file for our parameters

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(FILE_PATH), filter)) {
            Iterator<Path> iterator = entries.iterator();
            if (iterator.hasNext()) {
                Path entry = iterator.next();
                System.out.println(entry.toString());
                assertEquals(FILE_PATH + FILE_NAME, entry.toString().replace('\\', '/'));
            } else {
                fail("No matching file found");
            }
        }
    }

    @Test
    @DisplayName("Тест на обработку случая отсуствия файла по заданным фильтрам")
    void testThatGetPathWithFilterAndReturnedNoMatch() throws IOException {
        //Given: path to folder, where we need search file

        //When: create parameters for needed file
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(smallerThan(0))
            .and(magicNumber(0x89, 'P', 'X', 'G'))
            .and(globMatches("*.png"));
        //Then: we get no file for our parameters
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of(FILE_PATH), filter)) {
            Iterator<Path> iterator = entries.iterator();
            assertFalse(iterator.hasNext());
        }
    }

}
