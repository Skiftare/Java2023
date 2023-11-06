package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task12Test {
    @Test
    @DisplayName("Тест, который считает количество животных у которых вес больше роста")
    public void testThatGetEngListOfAnimalsAndReturnedCountAnimalsWithWeightAboveHeight() {
        // Получаем список животных из вспомогательного класс
        List <Animal> animals = ForAllTestsPatterns.getEngArray();
        Animal buf = new Animal("q", Animal.Type.DOG, Animal.Sex.M,12,12,13,true);
        animals.set(animals.size()-1,buf);
        int count = Task12.countAnimalsWithWeightAboveHeight(animals);

        assertEquals(1, count);
    }

    @Test
    @DisplayName("Тест со смешанным списком животных")
    public void testThatGetMixedListOfAnimalsAndReturnedCountAnimalsWithWeightAboveHeight() {
        // Получаем список животных из вспомогательного класса
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Считаем количество животных у которых вес больше роста
        int count = Task12.countAnimalsWithWeightAboveHeight(animals);


        assertEquals(0, count);
    }
}
