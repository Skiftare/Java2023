package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @DisplayName("Натуральные числа")
    @Test
    void testThatGetPozitiveNumbersAndReturnedLength() {
        assertEquals(3, Task2.countDigits(123));
        assertEquals(1, Task2.countDigits(1));
        assertEquals(5, Task2.countDigits(12305));
    }

    @DisplayName("Случай с 0")
    @Test
    void testThatGetZeroAndReturnedZero() {
        assertEquals(0, Task2.countDigits(0));
        assertEquals(0, Task2.countDigits(-0));
    }

    @DisplayName("Отрицательные числа")
    @Test
    void testThatGetNegativeNumbersAndReturnedLengthOfAbs() {
        assertEquals(2, Task2.countDigits(-24));
        assertEquals(4, Task2.countDigits(-2454));
        assertEquals(7, Task2.countDigits(-2478908));
    }
}
