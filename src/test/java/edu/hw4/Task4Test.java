package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Тест, который проверяет, у какого животного самое длинное имя")
    public void testThatGetListOfAnimalsAndReturnedAnimalWithTheLongestName() {

        // Создаем список животных
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Получаем животное с самым длинным именем
        Animal animalWithLongestName = Task4.findAnimalWithLongestName(animals);

        // Проверяем, что найденное животное имеет самое длинное имя
        assertEquals("Собака номер два", animalWithLongestName.getName());
    }
}
