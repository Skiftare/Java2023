package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

    @DisplayName("Случай с четной длинной строки")
    @Test
    void TestThatGetEvenLengthOfNumberAndReturnedPalindromeCheck() {
        assertTrue(Task5.isPalindromeDescendant(23336014));
        assertTrue(Task5.isPalindromeDescendant(13001120));
        assertTrue(Task5.isPalindromeDescendant(11211230));
    }

    @DisplayName("Случай с нечетной длинной строки")
    @Test
    void TestThatGetOddLengthOfNumberAndReturnedPalindromeCheck() {
        assertTrue(Task5.isPalindromeDescendant(121));
        assertFalse(Task5.isPalindromeDescendant(123));
    }

    @DisplayName("Сумма двух соседних цифр больше 10")
    @Test
    void testThatGetNumberWhereSumOfDigitsMore10AndReturnedPalindromeCheck() {

        assertTrue(Task5.isPalindromeDescendant(5683));
        assertTrue(Task5.isPalindromeDescendant(5582));//1010 -> 11
        assertTrue(Task5.isPalindromeDescendant(11));
    }

}
