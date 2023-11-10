package edu.hw6;

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
    void testOutputStreamComposition() throws IOException {
        // Создание временного файла
        Path tempFile = Files.createTempFile("output", ".txt");
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        try (OutputStream fileOutputStream = Files.newOutputStream(tempFile, StandardOpenOption.CREATE);
             OutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, new CRC32());
             OutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             Writer outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
             PrintWriter printWriter = new PrintWriter(outputStreamWriter)) {
            printWriter.print(expected);
        }

        // Проверка содержимого файла
        String fileContent = Files.readString(tempFile);

        assertEquals(expected, fileContent);

        // Удаление временного файла
        Files.delete(tempFile);
    }
}
