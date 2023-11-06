package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task17Test {
    @Test
    @DisplayName("Тестирование функции calculateAverageBites")
    public void testThatGetEngListOfAnimalsAndReturnedCalculatedAverageBites() {
        // Вызываем функцию с массивом на английском
        boolean result = Task17.calculateAverageBites(ForAllTestsPatterns.getEngArray());

        // Проверяем результат
        assertEquals(false, result);
    }

    @Test
    public void testThatGetRusListOfAnimalsAndReturnedCalculatedAverageBites() {
        // Вызываем функцию с массивом на русском
        boolean result = Task17.calculateAverageBites(ForAllTestsPatterns.getRusArray());

        // Проверяем результат
        assertEquals(true, result);
    }

    @Test
    public void testThatGetMixedListOfAnimalsAndReturnedCalculatedAverageBites() {
        // Вызываем функцию с мешаным массивом
        boolean result = Task17.calculateAverageBites(ForAllTestsPatterns.getMixedArray());

        // Проверяем результат
        assertEquals(true, result);
    }
}
