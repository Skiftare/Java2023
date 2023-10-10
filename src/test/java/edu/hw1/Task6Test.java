package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {

    @DisplayName("Стандартная серия тестов")
    @Test
    void testThatGetNumberAndReturnedLengthOfWayToConstant(){
        assertEquals(5, Task6.funK(6621));
        assertEquals(4, Task6.funK(6554));
        assertEquals(3, Task6.funK(1234));
    }

    @DisplayName("Некорректный ввод")
    @Test
    void testThatGetIncorrectInputAndReturnedError(){
        assertEquals(-1, Task6.funK(0));
        assertEquals(-1, Task6.funK(1000));
    }

}
