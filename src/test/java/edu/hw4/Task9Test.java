package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task9Test {
    @Test
    @DisplayName("Тест, который проверяет сумму лап у животных в списке")
    public void testSumOfPawsEngAnimals() {
        // Получаем список животных из вспомогательного класса
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Считаем сумму лап у животных
        int sumOfPaws = Task9.sumOfPaws(animals);

        // Проверяем, что сумма равна 16 (4 + 2 + 4 + 4 + 2)
        assertEquals(16, sumOfPaws);
    }

    @Test
    @DisplayName("Тест с ругим массивом")
    public void testSumOfPawsRusAnimals() {
        // Получаем список животных из вспомогательного класса
        List<Animal> animals = ForAllTestsPatterns.getRusArray();

        // Считаем сумму лап у животных
        int sumOfPaws = Task9.sumOfPaws(animals);

        // Проверяем, что сумма равна 18 (4 + 4 + 2 + 0 + 8)
        assertEquals(18, sumOfPaws);
    }
}
