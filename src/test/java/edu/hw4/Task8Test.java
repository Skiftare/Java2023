package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task8Test {
    @Test
    @DisplayName("Тест, который проверяет поиск самого тяжелого животного среди животных ниже k см")
    public void testThatGetListOfAnimalsAndReturnedHeaviestAnimalBelowHeight() {
        // Получаем список животных для тестирования
        List<Animal> animals = ForAllTestsPatterns.getRusArray();

        // Ищем самое тяжелое животное среди животных ниже 20 см
        int k = 20;
        Optional<Animal> heaviestAnimal = Task8.findHeaviestAnimalBelowHeight(animals, k);

        // Проверяем, что найденное животное имеет правильный вес
        assertEquals(2, heaviestAnimal.get().getWeight());
    }
}
