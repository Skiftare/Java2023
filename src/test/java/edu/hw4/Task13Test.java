package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class Task13Test {
    @Test
    @DisplayName("Тестирование функции findAnimalsWithMoreThanTwoWordsInName")
    public void testFindAnimalsWithMoreThanTwoWordsInName() {
        // Создаем список животных для тестирования
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();
        //Меняем два значения

        // Вызываем функцию для тестирования
        List<Animal> result = Task13.findAnimalsWithMoreThanTwoWordsInName(animals);
        //System.out.println(animals.get(animals.size()-1));
        //System.out.println(animals.get(animals.size()-2));
        // Проверяем результат
        assertEquals(2, result.size());
        assertEquals("Собака номер два", result.get(0).getName());
        assertEquals("Большая Птица", result.get(1).getName());
    }
}
