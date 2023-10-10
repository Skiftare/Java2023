package edu.hw1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {

    @DisplayName("Сдвиг вправо - серия тестов из условия + см.комментарий")
    @Test
    void testThatGetNumberAndShiftReturnedBitwiseRightShift() {
        assertEquals(4, Task7.rotateRight(8, 1));
        assertEquals(115, Task7.rotateRight(155, 3));//10011 011 -> 1 10011 01 -> 1 1 10011 0->01110011

    }
    @DisplayName("Сдвиг влево - серия тестов из условия + см.комментарий")
    @Test
    void testThatGetNumberAndShiftReturnedBitwiseLeftShift() {
        assertEquals(1, Task7.rotateLeft(16, 1));
        assertEquals(6, Task7.rotateLeft(17, 2));
        assertEquals(142, Task7.rotateLeft(456, 4));//1110 01000 -> 01000 1110

    }
    @DisplayName("Отрицательный сдвиг (ошибка)")
    @Test
    void testThatGetIncorrectInputAndReturnederror() {
        assertEquals(-1, Task7.rotateLeft(16, -1));
        assertEquals(-1, Task7.rotateRight(456, -4));

    }

}
