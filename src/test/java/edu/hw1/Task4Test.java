package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {


    @DisplayName("Строка состоит из 1 символа")
    @Test
    void testThatGetOneCharAndReturnedChar() {
        assertEquals("1", Task4.fixString("1"));
        assertEquals(" ", Task4.fixString(" "));
    }

    @DisplayName("Строка состоит из нечетного числа символов")
    @Test
    void testThatGetStringWithOddLengthAndReturnedFixedString() {
        assertEquals("1234567", Task4.fixString("2143657"));
        assertEquals("abcde", Task4.fixString("badce"));
    }

    @DisplayName("Строка состоит из четного числа символов")
    @Test
    void testThatGetStringWithEvenLengthAndReturnedFixedString() {
        assertEquals("1234", Task4.fixString("2143"));
        assertEquals("214365", Task4.fixString("123456"));
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
    }
}
