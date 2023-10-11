package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    @DisplayName("zeroZero")
    void testThatGetZeroAndZeroReturnedZero() {
        assertEquals(0, Task1.minutesToSeconds("00:00"));

    }

    @Test
    @DisplayName("standartTest-10:45")
    void testThatGetNormalTimeAndReturnedSecond() {
        assertEquals(10 * 60 + 45, Task1.minutesToSeconds("10:45"));
        assertEquals(10 * 60 + 45, Task1.minutesToSeconds(" 1   0: 4  5 "));
    }


    @Test
    @DisplayName("Некорректное количество секунд")
    void testThatGetTooManySecondsAndReturnedError() {
        assertEquals(-1, Task1.minutesToSeconds("00:61"));
    }

    @Test
    @DisplayName("Корректное количество секунд, но есть ведущие нули")
    void testThatGetNormalTimeWithManyZeroesAndReturnedTimeInSeconds() {
        assertEquals(10, Task1.minutesToSeconds("00:00000000000010"));
        assertEquals(10*60+10, Task1.minutesToSeconds("10:00000000000010"));
    }

    @Test
    @DisplayName("Минуты/Часы становятся отрицательными")
    void testThatGetNegativeTimeAndReturnedError() {
        assertEquals(-1, Task1.minutesToSeconds("-10:10"));
        assertEquals(-1, Task1.minutesToSeconds("10:-10"));
        assertEquals(-1, Task1.minutesToSeconds("-10:-10"));
    }

    @Test
    @DisplayName("Нарушения ввода (буквы, разделители, отсутствуют минуты")
    void testThatGetWrongFormatAndReturnedError() {
        assertEquals(-1, Task1.minutesToSeconds("10.10"));
        assertEquals(-1, Task1.minutesToSeconds("1у:10"));
        assertEquals(-1, Task1.minutesToSeconds("14:1t"));
        assertEquals(-1, Task1.minutesToSeconds("14:"));
        assertEquals(-1, Task1.minutesToSeconds("14:                  "));
    }

}
