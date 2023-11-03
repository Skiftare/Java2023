package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task15Test {

    @Test
    @DisplayName("Тестирование функции calculateTotalWeightByType")
    public void testThatGetListOfAnimalsAndReturnedCalculatedTotalWeightByType() {
        // Создаем список животных для тестирования
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Вызываем функцию для тестирования
        Map<Animal.Type, Integer> result = Task15.calculateTotalWeightByType(animals, 2, 4);

        // Создаем ожидаемый результат
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.CAT, 10,
            Animal.Type.BIRD, 3,
            Animal.Type.DOG, 15
        );

        // Проверяем результат
        assertEquals(expected, result);
    }
}
