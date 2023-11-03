package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task11Test {
    @Test
    @DisplayName("Тест, который находит животных, которые могут укусить и высота которых больше 100 см")
    public void testFindAnimalsWithBitesAndHeightAbove() {
        // Получаем список животных из вспомогательного класса
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Находим животных с высотой больше 39 см и которые могут укусить
        //(Если работает для произвольного k, то работает и для 100
        int height = 39;
        List<Animal> result = Task11.findAnimalsWithBitesAndHeightAbove(animals, height);

        // Проверяем, что в результате только Spike
        assertEquals(1, result.size());
        assertTrue(result.contains(new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true)));

       result = Task11.findAnimalsWithBitesAndHeightAbove(animals, 100);

        // Проверяем, что в результате только Spike
        assertEquals(0, result.size());

    }
}
