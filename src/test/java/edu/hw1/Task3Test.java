package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {

    @Test
    @DisplayName("Массивы между собой не равны. Вложенность есть")
    void testThatGetDifferentMassiveAndReturnedTrue() {
        assertTrue(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        assertTrue(Task3.isNestable(new int[]{3, 1}, new int[]{4, 0}));
    }
    @Test
    @DisplayName("Массивы между собой не равны. Вложенности нет")
    void testThatGetDifferentMassiveAndReturnedFalse() {
        assertFalse(Task3.isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
    }


    @Test
    @DisplayName("Случай равенства массивов")
    void testThatGetSameMassiveAndReturnedTrue() {
        assertFalse(Task3.isNestable(new int[]{3, 1}, new int[]{1, 3}));
        assertFalse(Task3.isNestable(new int[]{1, 3}, new int[]{3, 1}));
    }
}
