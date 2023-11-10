package edu.hw6;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import static edu.hw6.AbstractFilter.globMatches;
import static edu.hw6.AbstractFilter.largerThan;
import static edu.hw6.AbstractFilter.magicNumber;
import static edu.hw6.AbstractFilter.regexContains;
import static edu.hw6.AbstractFilter.smallerThan;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    public static final AbstractFilter regularFile = Files::isRegularFile;
    public static final AbstractFilter readable = Files::isReadable;

    @Test
    @DisplayName("Тест на поиск файла по заданным фильтрам")
    void testThatGetPathWithFilterAndReturnedFile() throws IOException {

        Path dir = Path.of("src/test/java/edu/hw6/");
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(smallerThan(100_000))
            .and(magicNumber(0x89, 'P', 'N', 'G'))
            .and(globMatches("*.png"));

        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {

            Iterator<Path> iterator = entries.iterator();
            if (iterator.hasNext()) {
                Path entry = iterator.next();
                assertEquals("src/test/java/edu/hw6/Рисунок1.png", entry.toString().replace('\\','/'));
            } else {
                fail("No matching file found");
            }
        }
    }

    @Test
    @DisplayName("Тест на обработку случая отсуствия файла по заданным фильтрам")
    void testThatGetPathWithFilterAndReturnedNoMatch() throws IOException {

        Path dir = Path.of("src/test/java/edu/hw6/");
        DirectoryStream.Filter<Path> filter = regularFile
            .and(readable)
            .and(smallerThan(0))
            .and(magicNumber(0x89, 'P', 'X', 'G'))
            .and(globMatches("*.png"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(dir, filter)) {
            Iterator<Path> iterator = entries.iterator();
            assertFalse(iterator.hasNext());
        }
    }



}
