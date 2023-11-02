package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static edu.hw4.Task6.findHeaviestAnimalByType;

public class Task6Test {
    @Test
    @DisplayName("Тест на проверку корректности работы поиска максимума для одного животного")
    void testThatGetArrayOfAnimalsAndReturnedMapOfHeaviest() {
        List<Animal> animals = List.of(
            new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 3, 25, 4, false),
            new Animal("Собака", Animal.Type.CAT, Animal.Sex.F, 5, 30, 8, true),
            new Animal("Попугай", Animal.Type.CAT, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Рыба", Animal.Type.CAT, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Паук", Animal.Type.CAT, Animal.Sex.M, 1, 2, 0, true)
        );

        Map<Animal.Type, Animal> heaviestAnimals = findHeaviestAnimalByType(animals);
        /*for (Map.Entry<Animal.Type, Animal> entry : heaviestAnimals.entrySet()) {
            System.out.println("Самое тяжелое животное вида " + entry.getKey() + ": " + entry.getValue().getName());
        }*/
    }
}
