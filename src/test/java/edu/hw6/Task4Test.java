package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class Task4Test {
    @Test
    @DisplayName("Тест на запись в файл строки через outputStreamComposition")
    void testGetPathWithStringAndRetunedFileInPathWithWrittenString() throws IOException {
        Path tempFile = Files.createTempFile("output", ".txt");
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        Task4.outputStreamComposition(expected,tempFile);
        String fileContent = Files.readString(tempFile);
        assertEquals(expected+'\n', fileContent);
        Files.delete(tempFile);
    }
}
