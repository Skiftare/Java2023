package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {
    String COPY_ONE = "test — копия";
    String ORIGIN_NAME = "test";
    @Test
    @DisplayName("Тест на корректное копирование файла")
    void testThatCloneFileAndReturnedCheckOfExistCopy() throws IOException {
        //Given: path to file
        Path originalFile = Files.createFile(Path.of(ORIGIN_NAME+".txt"));
        //When: we need to clone it 1 time
        Task2.cloneFile(originalFile);
        //Then: we get cloned file
        assertTrue(Files.exists(originalFile));
        assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+".txt")));
        //Clear memory
        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling(COPY_ONE+".txt"));
        assertFalse(Files.exists(originalFile));
        assertFalse(Files.exists(originalFile.resolveSibling(COPY_ONE+".txt")));
    }

    @Test
    @DisplayName("Тест на корректное создание множества копий файла")
    void testThatCloneFileManyTimesAndReturnedCheckOfExistAllCopies() throws IOException {
        //Given: path to file
        Path originalFile = Files.createFile(Path.of(ORIGIN_NAME+".txt"));
        //When: we need to clone it many times
        Task2.cloneFile(originalFile);
        for (int i = 2; i <= 5; i++) {
            Task2.cloneFile(originalFile);
        }
        //Then: we get cloned files
        assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+".txt")));
        for (int i = 2; i <= 5; i++) {
            assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+" (" + i + ").txt")));
        }
        //Clear memory
        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling(COPY_ONE+".txt"));
        for (int i = 2; i <= 5; i++) {
            Files.delete(originalFile.resolveSibling(COPY_ONE+" (" + i + ").txt"));
        }
    }

    @Nested
    @DisplayName("Тест на корректное копирование файлов с различными расширениями")
    public class TestsForDifferentExtensions {

        @Test
        @DisplayName("Тест на копирование .txt")
        void testThatCloneFileWithTXTAndReturnedCheckOfExistCopy() throws IOException {
            //Given: path to file
            Path originalFile = Files.createFile(Path.of(ORIGIN_NAME+".txt"));
            //When: we need to clone it
            Task2.cloneFile(originalFile);
            //Then: we get cloned files
            assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+".txt")));
            //Clear memory
            Files.delete(originalFile);
            Files.delete(originalFile.resolveSibling(COPY_ONE+".txt"));
        }

        @Test
        @DisplayName("Тест на копирование .docx")
        void testThatCloneFileWithDOCXExtentionAndReturnedCheckOfExistCopy() throws IOException {
            //Given: path to file
            Path originalFile = Files.createFile(Path.of(ORIGIN_NAME+".docx"));
            //When: we need to clone it
            Task2.cloneFile(originalFile);
            //Then: we get cloned files
            assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+".docx")));
            //Clear memory
            Files.delete(originalFile);
            Files.delete(originalFile.resolveSibling(COPY_ONE+".docx"));

        }

        @Test
        @DisplayName("Тест на копирование .ppt")
        void testThatCloneFileWithPPTExtentionAndReturnedCheckOfExistCopy() throws IOException {
            //Given: path to file
            Path originalFile = Files.createFile(Path.of(ORIGIN_NAME+".ppt"));
            //When: we need to clone it
            Task2.cloneFile(originalFile);
            //Then: we get cloned files
            assertTrue(Files.exists(originalFile.resolveSibling(COPY_ONE+".ppt")));
            //Clear memory
            Files.delete(originalFile);
            Files.delete(originalFile.resolveSibling(COPY_ONE+".ppt"));
        }
    }
}
