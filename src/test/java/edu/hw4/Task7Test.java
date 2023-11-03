package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task7Test {
    @Test
    @DisplayName("Тест, который проверяет поиск K-го самого старого животного")
    public void testFindKthOldestAnimal() {
        // Создаем список животных с разными возрастами

        // Создаем список всех животных
        List<Animal> animals = ForAllTestsPatterns.getRusArray();

        // Ищем K-е самое старое животное
        int k = 3;
        Optional<Animal> kthOldestAnimal = Task7.findKthOldestAnimal(animals, k);

        // Проверяем, что найденное животное имеет правильный возраст
        assertEquals(2, kthOldestAnimal.get().getAge());
    }
}
