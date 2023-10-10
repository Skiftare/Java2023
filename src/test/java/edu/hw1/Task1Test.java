package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @DisplayName("zeroZero")
    @Test
    void zero_zero() {
        assertEquals(0, Task1.minutesToSeconds("00:00"));

    }

    @DisplayName("standartTest-10:45")
    @Test
    void standart() {
        assertEquals(10 * 60 + 45, Task1.minutesToSeconds("10:45"));
        assertEquals(10 * 60 + 45, Task1.minutesToSeconds(" 1   0: 4  5 "));
    }

    @DisplayName("Некорректное количество секунд")
    @Test
    void a_lot_secs() {
        assertEquals(-1, Task1.minutesToSeconds("00:61"));
    }

    @DisplayName("Корректное количество секунд, но есть ведущие нули")
    @Test
    void not_a_lot_secs() {
        assertEquals(10, Task1.minutesToSeconds("00:00000000000010"));
        assertEquals(10*60+10, Task1.minutesToSeconds("10:00000000000010"));
    }

    @DisplayName("Минуты/Часы становятся отрицательными")
    @Test
    void minuses() {
        assertEquals(-1, Task1.minutesToSeconds("-10:10"));
        assertEquals(-1, Task1.minutesToSeconds("10:-10"));
        assertEquals(-1, Task1.minutesToSeconds("-10:-10"));
    }
    
    @DisplayName("Нарушения ввода (буквы, разделители, отсутствуют минуты")
    @Test
    void wrongFormat() {
        assertEquals(-1, Task1.minutesToSeconds("10.10"));
        assertEquals(-1, Task1.minutesToSeconds("1у:10"));
        assertEquals(-1, Task1.minutesToSeconds("14:1t"));
        assertEquals(-1, Task1.minutesToSeconds("14:"));
        assertEquals(-1, Task1.minutesToSeconds("14:                  "));
    }

}
