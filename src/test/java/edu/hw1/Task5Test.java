package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {
    @Test
    void standart() {
        assertTrue(Task5.isPalindromeDescendant(11211230));
        assertTrue(Task5.isPalindromeDescendant(13001120));
        assertTrue(Task5.isPalindromeDescendant(23336014));
        assertTrue(Task5.isPalindromeDescendant(11));
    }

    @Test
    void oddLengthOfNum() {
        assertTrue(Task5.isPalindromeDescendant(121));
        assertFalse(Task5.isPalindromeDescendant(123));
    }

    @Test
    void sumNumbersMore10() {

        assertTrue(Task5.isPalindromeDescendant(5683));
        assertTrue(Task5.isPalindromeDescendant(5582));//1010 -> 11
    }

}
