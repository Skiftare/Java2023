package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static edu.hw6.Task2.createNameByNumber;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    private static final String ORIGIN_NAME = "test";

    @Test
    @DisplayName("Тест на корректное создание имен для копий файла")
    void testThatGetNumberOfCopyAndCreateNameForIt() {
        //given: name, extension and serial number of the copy
        String extension = ".txt";
        //when: create names for copy by the function
        String copyForFirst = createNameByNumber(ORIGIN_NAME, 1, extension);
        String copyForNotFisrt = createNameByNumber(ORIGIN_NAME, 1234, extension);
        //then: check for correct names for copies
        assertEquals("test - копия.txt", copyForFirst);
        assertEquals("test - копия (1234).txt", copyForNotFisrt);
    }

    @Test
    @DisplayName("Тест на корректное копирование файла")
    void testThatCloneFileAndReturnedCheckOfExistCopy() throws IOException {
        //Given: path to file
        Path originalFile = Files.createFile(Path.of(ORIGIN_NAME + ".txt"));
        //When: we need to clone it 1 time
        Task2.cloneFile(originalFile);
        //Then: we get cloned file
        assertTrue(Files.exists(originalFile));
        assertTrue(Files.exists(originalFile.resolveSibling(createNameByNumber(ORIGIN_NAME, 1, ".txt"))));
        //Clear memory
        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling(createNameByNumber(ORIGIN_NAME, 1, ".txt")));
        assertFalse(Files.exists(originalFile));
        assertFalse(Files.exists(originalFile.resolveSibling(createNameByNumber(ORIGIN_NAME, 1, ".txt"))));
    }

    @Test
    @DisplayName("Тест на корректное создание множества копий файла")
    void testThatCloneFileManyTimesAndReturnedCheckOfExistAllCopies() throws IOException {
        //Given: path to file
        Path originalFile = Files.createFile(Path.of(ORIGIN_NAME + ".txt"));
        //When: we need to clone it many times
        Task2.cloneFile(originalFile);
        for (int i = 2; i <= 5; i++) {
            Task2.cloneFile(originalFile);
        }
        //Then: we get cloned files
        for (int i = 1; i <= 5; i++) {
            assertTrue(Files.exists(originalFile.resolveSibling(createNameByNumber(ORIGIN_NAME, i, ".txt"))));
        }
        //Clear memory
        Files.delete(originalFile);
        for (int i = 1; i <= 5; i++) {
            Files.delete(originalFile.resolveSibling(createNameByNumber(ORIGIN_NAME, i, ".txt")));
        }
    }

    @ParameterizedTest
    @DisplayName("Тест на копирование файла с разными расширениями")
    @ValueSource(strings = {".txt", ".docx", ".ppt"})
    void testThatCloneFileAndCheckExistence(String extension) throws IOException {
        // Given: path to original file
        Path originalFile = Files.createFile(Path.of(ORIGIN_NAME + extension));

        // When: we need to clone it
        Task2.cloneFile(originalFile);

        // Then: we get cloned file
        String copyName = createNameByNumber(ORIGIN_NAME, 1, extension);
        assertTrue(Files.exists(originalFile.resolveSibling(copyName)));

        // Clear memory
        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling(copyName));
    }
}
