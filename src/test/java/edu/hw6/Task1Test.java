package edu.hw6;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {

    private static final String TEST_FILE_PATH = "test_data.txt";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE_PATH);
        diskMap.clear();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции put() для самописного класса")
    void testThatPutValsAndReturnedCheckOfTheyExist() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Тест на корректную отработку функции remove() для самописного класса")
    void testThatRemoveValsAndReturnedCheckOfTheyNotExist() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.remove("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key1")).isNull();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции size() для самописного класса")
    void testThatPutValsAndReturnedCheckOfSizeOfMap() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции isEmpty() для самописного класса")
    void testThatPutValsAndReturnedCheckOfIsEmptyMap() {
        assertThat(diskMap.isEmpty()).isTrue();

        diskMap.put("key1", "value1");

        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Тест на то, что в DiskMap нет чего-то, что мы туда не клали")
    void testThatPutValsAndReturnedContainsValsByKey() {
        diskMap.put("key1", "value1");

        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsKey("key2")).isFalse();
        assertThat(diskMap.size()).isEqualTo(1);
        diskMap.put("key2", "value2");
        assertThat(diskMap.keySet()).containsExactlyInAnyOrder("key1", "key2");

    }

    @Test
    @DisplayName("Тест на корректную отработку функции containsValue() для самописного класса")
    void testThatPutValsAndReturnedContainsValues() {
        diskMap.put("key1", "value1");

        assertThat(diskMap.containsValue("value1")).isTrue();
        assertThat(diskMap.containsValue("value2")).isFalse();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции putAll() для самописного класса")
    void testThatPutValsAsOneMapAndReturnedCheckOfSuccessfulAdd() {
        Map<String, String> testData = new HashMap<>();
        testData.put("key1", "value1");
        testData.put("key2", "value2");

        diskMap.putAll(testData);

        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Тест на корректную отработку функции clear() для самописного класса")
    void testThatClearMapReturnedIfMapEmpty() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        diskMap.clear();

        assertThat(diskMap.size()).isEqualTo(0);
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции entrySet() для самописного класса")
    void testThatPutValsAndReturnedChekOfFuctionEntrySet() {
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");

        assertThat(diskMap.entrySet()).containsExactlyInAnyOrder(
            Map.entry("key1", "value1"),
            Map.entry("key2", "value2")
        );
    }
}
