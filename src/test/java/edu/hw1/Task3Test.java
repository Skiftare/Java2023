package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task3Test {
    @Test
    void standart() {
        assertTrue(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        assertTrue(Task3.isNestable(new int[]{3, 1}, new int[]{4, 0}));
        assertFalse(Task3.isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
    }

    @Test
    void same_mas() {
        assertFalse(Task3.isNestable(new int[]{3, 1}, new int[]{1, 3}));
        assertFalse(Task3.isNestable(new int[]{1, 3}, new int[]{3, 1}));
    }
}
