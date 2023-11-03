package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task10Test {
    @Test
    @DisplayName("Тест, который проверяет список животных с несовпадающим возрастом и количеством лап")
    public void testThatGetListOfAnimalsAndReturnedAnimalsWithMismatchedAgeAndPaws() {
        // Получаем список животных из вспомогательного класса
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Ищем животных с несовпадающим возрастом и количеством лап
        List<Animal> result = Task10.findAnimalsWithMismatchedAgeAndPaws(animals);
        //System.out.println(result);


        assertEquals(3, result.size());
        assertTrue(result.contains(new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 101, 5, 0, false)));
        assertTrue(result.contains(new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, false)));
        assertTrue(result.contains(new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true)));
    }
}
