package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Тест на корректное копирование файла")
    void testThatCloneFileAndReturnedCheckOfExistCopy() throws IOException {
        Path originalFile = Files.createFile(Path.of("test.txt"));

        Task2.cloneFile(originalFile);

        assertThat(Files.exists(originalFile)).isTrue();
        assertThat(Files.exists(originalFile.resolveSibling("test — копия.txt"))).isTrue();

        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling("test — копия.txt"));
    }

    @Test
    @DisplayName("Тест на корректное создание множества копий файла")
    void testThatCloneFileManyTimesAndReturnedCheckOfExistAllCopies() throws IOException {
        Path originalFile = Files.createFile(Path.of("test.txt"));
        for (int i = 2; i <= 5; i++) {
            Task2.cloneFile(originalFile);
            assertThat(Files.exists(originalFile.resolveSibling("test — копия.txt"))).isTrue();
            assertThat(Files.exists(originalFile.resolveSibling("test — копия (" + i + ").txt"))).isTrue();
        }
        Files.delete(originalFile);
        Files.delete(originalFile.resolveSibling("test — копия.txt"));
        for (int i = 2; i <= 5; i++) {
            Files.delete(originalFile.resolveSibling("test — копия (" + i + ").txt"));
        }
    }

    @Test
    @DisplayName("Тест на корректное копирование файлов с различными расширениями")
    void testThatCloneFileWithDifferentExtentionAndReturnedCheckOfExistCopy() throws IOException {
        Path originalFile = Files.createFile(Path.of("test.txt"));
        Task2.cloneFile(originalFile);
        assertThat(Files.exists(originalFile.resolveSibling("test — копия.txt"))).isTrue();
        Path renamedFile = Files.move(originalFile, originalFile.resolveSibling("test.docx"));
        Task2.cloneFile(renamedFile);
        assertThat(Files.exists(renamedFile.resolveSibling("test — копия.docx"))).isTrue();
        Files.delete(renamedFile);
        Files.delete(renamedFile.resolveSibling("test — копия.docx"));
    }
}
