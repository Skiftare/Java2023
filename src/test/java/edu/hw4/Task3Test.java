package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    @Test
    @DisplayName("Тест, который проверяет подсчет количества животных каждого вида")
    public void testThatGetListOfAnimalsAndReturnedCountOfEachType() {
        // Создаем список животных

        Task3 incomeTask = new Task3();
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Создаем карту для подсчета количества животных каждого вида
        Map<Animal.Type, Integer> animalCount = incomeTask.countAnimalsByType(animals);

        // Проверяем, что подсчитанное количество животных каждого вида соответствует ожидаемому
        assertEquals(2, animalCount.get(Animal.Type.CAT));
        assertEquals(1, animalCount.get(Animal.Type.DOG));
        assertEquals(2, animalCount.get(Animal.Type.BIRD));
        assertEquals(null, animalCount.get(Animal.Type.FISH));
        assertEquals(null, animalCount.get(Animal.Type.SPIDER));
    }

}
