package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task6Test {
    @Test
    @DisplayName("Тест, который проверяет самое тяжелое животное каждого вида")
    public void testThatGetHeaviestAnimalByType() {
        // Создаем список животных разных видов с разным весом
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();
        // Создаем карту для хранения самых тяжелых животных каждого вида
        Map<Animal.Type, Animal> heaviestAnimals = Task6.findHeaviestAnimalByType(animals);
        Map<Animal.Type, Animal> expected = new HashMap<>();
        // Добавляем самое тяжелое животное каждого вида в карту
        for(Animal ani:animals){

            if(!expected.containsKey(ani.getType()) || heaviestAnimals.get(ani.getType()).getWeight()<=ani.getWeight()){
                expected.put(ani.getType(),ani);
            }
        }



        // Проверяем, что в карте содержатся правильные животные
        //CAT, DOG, BIRD, FISH, SPIDER
        for(Animal ani: animals){
            assertEquals(expected.get(ani.getType()), heaviestAnimals.get(ani.getType()));
        }
    }
}
