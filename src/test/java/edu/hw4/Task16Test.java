package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task16Test {
    @Test
    @DisplayName("Тестирование функции sortAnimals")
    public void testThatGetListOfAnimalsAndReturnedSortedListOfAnimals() {
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();
        List<Animal> result = Task16.sortAnimals(animals);

        List<Animal> expected = Arrays.asList(
            new Animal("Кот", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, false),
            new Animal("Кот1", Animal.Type.CAT, Animal.Sex.M, 3, 25, 6, false),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 4, false),
            new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true),
            new Animal("Собака номер два", Animal.Type.DOG, Animal.Sex.F, 5, 30, 10, false),
            new Animal("Jerry", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Tweety(X)", Animal.Type.BIRD, Animal.Sex.F, 1, 5, 0, false),
            new Animal("Большая Птица", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 2, false),
            new Animal("Рыба", Animal.Type.FISH, Animal.Sex.M, 1, 5, 1, false),
            new Animal("Паук", Animal.Type.SPIDER, Animal.Sex.M, 1, 2, 0, true)
        );

        // Проверяем результат
        assertArrayEquals(new List[] {expected}, new List[] {result});
    }
}
