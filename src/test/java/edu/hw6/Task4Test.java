package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Тест на запись в файл строки через outputStreamComposition")
    void testGetPathWithStringAndRetunedFileInPathWithWrittenString() throws IOException {
        //Given: tempFile and string to write
        Path tempFile = Files.createTempFile("output", ".txt");
        String expected = "Programming is learned by writing programs. ― Brian Kernighan";
        //When: trying to write in file
        Task4.outputStreamComposition(expected, tempFile);
        String fileContent = Files.readString(tempFile);
        //Then: check for succ write
        assertEquals(expected + '\n', fileContent); //Тут решил не ставить StringBuilder, потому что.. Ну, это из пушки по воробьям. Производительность и память жалко.
        Files.delete(tempFile);
    }
}
