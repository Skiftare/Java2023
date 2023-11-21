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

    private static DiskMap DISK_MAP;
    private static final String TEST_FILE_PATH = "src/test/java/edu/hw6/resources/test_data.dat";
    private static final String FIRST_VAL_OF_TEST_MAP = "value1 for testing DiskMap";
    private static final String FIRST_KEY_OF_TEST_MAP = "key1 for testing DiskMap";
    private static final String SECOND_VAL_OF_TEST_MAP = "value2 for testing DiskMap";
    private static final String SECOND_KEY_OF_TEST_MAP = "key2 for testing DiskMap";


    @BeforeEach
    void setUp() {
        DISK_MAP = new DiskMap(TEST_FILE_PATH);
        DISK_MAP.clear();
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
        // empty DISK_MAP

        //put vals
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //check that vals in map
        assertThat(DISK_MAP.get(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);
        assertThat(DISK_MAP.get(SECOND_KEY_OF_TEST_MAP)).isEqualTo(SECOND_VAL_OF_TEST_MAP);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции remove() для самописного класса")
    void testThatRemoveValsAndReturnedCheckOfTheyNotExist() {
        // map with two vals
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //delete val1
        assertThat(DISK_MAP.remove(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);

        //val1 doesnt exist
        assertThat(DISK_MAP.get(FIRST_KEY_OF_TEST_MAP)).isNull();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции size() для самописного класса")
    void testThatPutValsAndReturnedCheckOfSizeOfMap() {
        // map with two vals
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //we have two vals in map
        assertThat(DISK_MAP.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции isEmpty() -> True для самописного класса")
    void testThatPutNoValsAndReturnedTrueIsEmptyMap() {
        // empty DISK_MAP

        //map is empty
        assertThat(DISK_MAP.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции isEmpty() -> False для самописного класса")
    void testThatPutValsAndReturnedFalseIsEmptyMap() {
        // DISK_MAP with 1 element
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);

        //map is NOT empty
        assertThat(DISK_MAP.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Тест на то, что в DISK_MAP нет чего-то, что мы туда не клали")
    void testThatPutValsAndReturnedContainsValsByKey() {
        // map with one val
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);

        //map contains only this val
        assertThat(DISK_MAP.containsKey(FIRST_KEY_OF_TEST_MAP)).isTrue();
        assertThat(DISK_MAP.containsKey(SECOND_KEY_OF_TEST_MAP)).isFalse();
        assertThat(DISK_MAP.size()).isEqualTo(1);

        //put one more val
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //map contains only these two vals
        assertThat(DISK_MAP.keySet()).containsExactlyInAnyOrder(FIRST_KEY_OF_TEST_MAP, SECOND_KEY_OF_TEST_MAP);

    }

    @Test
    @DisplayName("Тест на корректную отработку функции containsValue() для самописного класса")
    void testThatPutValsAndReturnedContainsValues() {
        //map with one val
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);

        //map containsValue() return True only for this one val
        assertThat(DISK_MAP.containsValue(FIRST_VAL_OF_TEST_MAP)).isTrue();
        assertThat(DISK_MAP.containsValue(SECOND_VAL_OF_TEST_MAP)).isFalse();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции putAll() для самописного класса")
    void testThatPutValsAsOneMapAndReturnedCheckOfSuccessfulAdd() {
        Map<String, String> testData = new HashMap<>();
        //Empty DISK_MAP and Map<> in Test
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //put one map with two vals into DISK_MAP
        DISK_MAP.putAll(testData);

        //in DISK_MAP we have vals, which were in Map<> in Test
        assertThat(DISK_MAP.get(FIRST_KEY_OF_TEST_MAP)).isEqualTo(FIRST_VAL_OF_TEST_MAP);
        assertThat(DISK_MAP.get(SECOND_KEY_OF_TEST_MAP)).isEqualTo(SECOND_VAL_OF_TEST_MAP);
    }

    @Test
    @DisplayName("Тест на корректную отработку функции clear() для самописного класса")
    void testThatClearMapReturnedIfMapEmpty() {
        // map with two vals
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        // we clear() map
        DISK_MAP.clear();

        //we don't have anything in map
        assertThat(DISK_MAP.size()).isEqualTo(0);
        assertThat(DISK_MAP.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Тест на корректную отработку функции entrySet() для самописного класса")
    void testThatPutValsAndReturnedChekOfFuctionEntrySet() {
        // map with two vals
        DISK_MAP.put(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP);
        DISK_MAP.put(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP);

        //we re-create our map into set

        // contains exactly same elements
        assertThat(DISK_MAP.entrySet()).containsExactlyInAnyOrder(
            Map.entry(FIRST_KEY_OF_TEST_MAP, FIRST_VAL_OF_TEST_MAP),
            Map.entry(SECOND_KEY_OF_TEST_MAP, SECOND_VAL_OF_TEST_MAP)
        );
    }
}
