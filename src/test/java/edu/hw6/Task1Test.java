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

    private DiskMap diskMap;
    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/resources/test_data.dat";
    private static final String FIRST_VAL_OF_TEST_MAP = "value1 for testing DiskMap";
    private static final String FIRST_KEY_OF_TEST_MAP = "key1 for testing DiskMap";
    private static final String SECOND_VAL_OF_TEST_MAP = "value2 for testing DiskMap";
    private static final String SECOND_KEY_OF_TEST_MAP = "key2 for testing DiskMap";


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
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
        //check that vals in map
        assertThat(diskMap.get(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);
        assertThat(diskMap.get(SECOND_KEY_OF_TEST_MAP)).isEqualTo(SECOND_VAL_OF_TEST_MAP);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции remove() для самописного класса")
    void testThatRemoveValsAndReturnedCheckOfTheyNotExist() {
        // map with two vals
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
        //delete val1
        assertThat(diskMap.remove(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);
        //val1 doesnt exist
        assertThat(diskMap.get(FIRST_KEY_OF_TEST_MAP)).isNull();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции size() для самописного класса")
    void testThatPutValsAndReturnedCheckOfSizeOfMap() {
        // map with two vals
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
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
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        //map is NOT empty
        assertThat(diskMap.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Тест на то, что в DiskMap нет чего-то, что мы туда не клали")
    void testThatPutValsAndReturnedContainsValsByKey() {
        // map with one val
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        //map contains only this val
        assertThat(diskMap.containsKey(FIRST_KEY_OF_TEST_MAP)).isTrue();
        assertThat(diskMap.containsKey(SECOND_KEY_OF_TEST_MAP)).isFalse();
        assertThat(diskMap.size()).isEqualTo(1);
        //put one more val
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
        //map contains only these two vals
        assertThat(diskMap.keySet()).containsExactlyInAnyOrder(FIRST_KEY_OF_TEST_MAP, SECOND_KEY_OF_TEST_MAP);

    }

    @Test
    @DisplayName("Тест на корректную отработку функции containsValue() для самописного класса")
    void testThatPutValsAndReturnedContainsValues() {
        //map with one val
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        //map containsValue() return True only for this one val
        assertThat(diskMap.containsValue(FIRST_VAL_OF_TEST_MAP)).isTrue();
        assertThat(diskMap.containsValue(SECOND_VAL_OF_TEST_MAP)).isFalse();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции putAll() для самописного класса")
    void testThatPutValsAsOneMapAndReturnedCheckOfSuccessfulAdd() {
        Map<String, String> testData = new HashMap<>();
        //Empty DiskMap and Map<> in Test
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
        //put one map with two vals into DiskMap
        diskMap.putAll(testData);
        //in DiskMap we have vals, which were in Map<> in Test
        assertThat(diskMap.get(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);
        assertThat(diskMap.get(SECOND_KEY_OF_TEST_MAP)).isEqualTo(SECOND_VAL_OF_TEST_MAP);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции clear() для самописного класса")
    void testThatClearMapReturnedIfMapEmpty() {
        // map with two vals
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
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
        diskMap.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        diskMap.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);
        //we re-create our map into set
        // contains exactly same elements
        assertThat(diskMap.entrySet()).containsExactlyInAnyOrder(
            Map.entry(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP),
            Map.entry(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP)
        );
    }
}
