package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Task14Test {
    @Test
    @DisplayName("")
    public void testHasDogWithHeightAbove() {
        // Создаем список животных для тестирования
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();
        //Меняем два значения

        // Вызываем функцию для тестирования
        boolean result = Task14.hasDogWithHeightAbove(animals,4);
        assertEquals(true,result);
        result = Task14.hasDogWithHeightAbove(animals,50);
        assertEquals(false,result);
    }
}
