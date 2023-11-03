package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static edu.hw4.Task1.sortAnimalsByHeight;
import static edu.hw4.Task2.sortAnimalsByWeightAndSelectTopK;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    @Test
    @DisplayName("Тест, который проверяет сортировку животных по весу от самого тяжелого к самому легкому")
    public void testThatGetAnimalsListAndReturnedListSortedByWeight() {
        // Создаем список животныхs
        List<Animal> animals = ForAllTestsPatterns.getRusArray();
        // Отсортировать животных по весу от самого тяжелого к самому легкому
        animals.sort((a1, a2) -> Integer.compare(a2.getWeight(), a1.getWeight()));

        // Выбрать первые k животных
        int k = 3;
        List<Animal> selectedAnimals = sortAnimalsByWeightAndSelectTopK(animals, Math.min(k, animals.size()));

        // Проверить, что выбранные животные соответствуют ожидаемому порядку
        assertEquals("Собака", selectedAnimals.get(0).getName());
        assertEquals("Кот", selectedAnimals.get(1).getName());
        assertEquals("Птица", selectedAnimals.get(2).getName());
    }
}
