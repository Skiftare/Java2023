package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task18Test {
    @Test
    @DisplayName("Тестирование функции findHeaviestFish()")
    public void testFindHeaviestFish() {

        List<List<Animal>> fishLists = new ArrayList<>();
        fishLists.add(ForAllTestsPatterns.getEngArray());
        fishLists.add(ForAllTestsPatterns.getRusArray());
        fishLists.add(ForAllTestsPatterns.getMixedArray());

// Вызываем функцию для тестирования
        Animal result = Task18.findHeaviestFish(fishLists);

// Проверяем, что результат соответствует ожиданиям
        assertEquals("Рыба", result.name());
        assertEquals(Animal.Type.FISH, result.type());
    }

}
