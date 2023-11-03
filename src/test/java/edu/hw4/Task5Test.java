package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    @Test
    @DisplayName("Тест, который проверяет, каких животных больше: самцов или самок")
    public void testThatGetDominantSex() {


        // Создаем список животных
        List<Animal> animals = ForAllTestsPatterns.getRusArray();


        // Получаем доминирующий пол
        Animal.Sex dominantSex = Task5.findDominantSex(animals);

        // Проверяем, каких животных больше: самцов или самок
        assertEquals(Animal.Sex.M, dominantSex);
    }
}
