package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @DisplayName("Стандартная серия тестов")
    @Test
    void standart() {
        assertEquals("214365", Task4.fixString("123456"));
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals("abcde", Task4.fixString("badce"));
    }
    @DisplayName("Строка состоит из 1 символа")
    @Test
    void solo() {
        assertEquals("1", Task4.fixString("1"));
    }

    @DisplayName("Строка состоит из нечетного числа символов")
    @Test
    void oddLength() {
        assertEquals("1234567", Task4.fixString("2143657"));
    }

    @DisplayName("Строка состоит из четного числа символов")
    @Test
    void evenLength() {
        assertEquals("1234", Task4.fixString("2143"));
    }
}
