package edu.hw6;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class Task1Test {

    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/resources/test_data.dat";
    private DiskMap diskMap;

    @BeforeEach
    void setUp() {
        diskMap = new DiskMap(TEST_FILE_PATH);
        diskMap.clear();
    }

    @AfterEach
    void cleanUp() {
        File file = new File(TEST_FILE_PATH);
        if(!file.delete()){
            fail("Удаление файла после отработки теста не произошло");
        }
    }

    @Test
    @DisplayName("Тест на корректную отработку функции put() для самописного класса")
    void testThatPutValsAndReturnedCheckOfTheyExist() {
        // empty diskMap
        //put vals
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        //check that vals in map
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Тест на корректную отработку функции remove() для самописного класса")
    void testThatRemoveValsAndReturnedCheckOfTheyNotExist() {
        // map with two vals
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        //delete val1
        assertThat(diskMap.remove("key1")).isEqualTo("value1");
        //val1 doesnt exist
        assertThat(diskMap.get("key1")).isNull();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции size() для самописного класса")
    void testThatPutValsAndReturnedCheckOfSizeOfMap() {
        // map with two vals
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        //we have two vals in map
        assertThat(diskMap.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции isEmpty() -> True для самописного класса")
    void testThatPutNoValsAndReturnedTrueIsEmptyMap() {
        // empty diskMap
        //map is empty
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции isEmpty() -> False для самописного класса")
    void testThatPutValsAndReturnedFalseIsEmptyMap() {
        // diskMap with 1 element
        diskMap.put("key1", "value1");
        //map is NOT empty
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Тест на то, что в DiskMap нет чего-то, что мы туда не клали")
    void testThatPutValsAndReturnedContainsValsByKey() {
        // map with one val
        diskMap.put("key1", "value1");
        //map contains only this val
        assertThat(diskMap.containsKey("key1")).isTrue();
        assertThat(diskMap.containsKey("key2")).isFalse();
        assertThat(diskMap.size()).isEqualTo(1);
        //put one more val
        diskMap.put("key2", "value2");
        //map contains only these two vals
        assertThat(diskMap.keySet()).containsExactlyInAnyOrder("key1", "key2");

    }

    @Test
    @DisplayName("Тест на корректную отработку функции containsValue() для самописного класса")
    void testThatPutValsAndReturnedContainsValues() {
        //map with one val
        diskMap.put("key1", "value1");
        //map containsValue() return True only for this one val
        assertThat(diskMap.containsValue("value1")).isTrue();
        assertThat(diskMap.containsValue("value2")).isFalse();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции putAll() для самописного класса")
    void testThatPutValsAsOneMapAndReturnedCheckOfSuccessfulAdd() {
        Map<String, String> testData = new HashMap<>();
        //Empty DiskMap and Map<> in Test
        testData.put("key1", "value1");
        testData.put("key2", "value2");
        //put one map with two vals into DiskMap
        diskMap.putAll(testData);
        //in DiskMap we have vals, which were in Map<> in Test
        assertThat(diskMap.get("key1")).isEqualTo("value1");
        assertThat(diskMap.get("key2")).isEqualTo("value2");
    }

    @Test
    @DisplayName("Тест на корректную отработку функции clear() для самописного класса")
    void testThatClearMapReturnedIfMapEmpty() {
        // map with two vals
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        // we clear() map
        diskMap.clear();
        //we don't have anything in map
        assertThat(diskMap.size()).isEqualTo(0);
        assertThat(diskMap.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции entrySet() для самописного класса")
    void testThatPutValsAndReturnedChekOfFuctionEntrySet() {
        // map with two vals
        diskMap.put("key1", "value1");
        diskMap.put("key2", "value2");
        //we re-create our map into set
        //set contains exactly same elements
        assertThat(diskMap.entrySet()).containsExactlyInAnyOrder(
            Map.entry("key1", "value1"),
            Map.entry("key2", "value2")
        );
    }
}
