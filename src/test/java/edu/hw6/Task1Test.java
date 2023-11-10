package edu.hw6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class Task1Test {
    private static final String TEST_FILE_PATH = "test_data.dat";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE_PATH);
    }

    @Test
    void putAndGet() {
        diskMap.clear();

        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");


        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }

    @Test
    void remove() {
        diskMap.clear();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.remove("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key1")).isNull();
    }

    @Test
    void size() {
        diskMap.clear();
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    void isEmpty() {
        diskMap.clear();
        assertThat(diskMap.isEmpty()).isTrue();

        diskMap.put("key1", "value1");

        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    void containsKey() {
        diskMap.clear();
        diskMap.put("key1", "value1");

        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsKey("key2")).isFalse();
    }

    @Test
    void containsValue() {
        diskMap.clear();
        diskMap.put("key1", "value1");

        assertThat(diskMap.containsValue("value1")).isTrue();
        assertThat(diskMap.containsValue("value2")).isFalse();
    }

    @Test
    void loadFromFile() throws IOException {
        // Создание временного файла и запись данных
        File tempFile = File.createTempFile("temp", ".txt");
        Path tempFilePath = tempFile.toPath();
        Files.writeString(tempFilePath, "key1:value1\nkey2:value2");

        // Создание экземпляра DiskMap с временным файлом
        DiskMap tempDiskMap = new DiskMap(tempFilePath.toString());

        assertThat(tempDiskMap.get("key1")).isEqualTo("value1");
        assertThat(tempDiskMap.get("key2")).isEqualTo("value2");

        // Удаление временного файла
        Files.delete(tempFilePath);
    }

    @Test
    void saveToFile() throws IOException {
        // Создание временного файла
        File tempFile = File.createTempFile("temp", ".txt");
        Path tempFilePath = tempFile.toPath();

        // Создание экземпляра DiskMap с временным файлом
        DiskMap tempDiskMap = new DiskMap(tempFilePath.toString());

        tempDiskMap.put("key1", "value1");
        tempDiskMap.put("key2", "value2");

        // Загрузка данных из временного файла
        Map<String, String> loadedData = Files.lines(tempFilePath)
            .map(line -> line.split(":"))
            .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));

        assertThat(loadedData).containsEntry("key1", "value1");
        assertThat(loadedData).containsEntry("key2", "value2");

        // Удаление временного файла
        Files.delete(tempFilePath);
    }
}
