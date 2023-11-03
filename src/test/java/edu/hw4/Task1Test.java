package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static edu.hw4.Task1.sortAnimalsByHeight;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Тест сортировки животных по росту")
    public void testThatGetListOfAnimalsAndSortedThemByHeight() {

        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        List<Animal> sortedAnimals = sortAnimalsByHeight(animals);

        List<Animal> expectedSortedAnimals = Arrays.asList(
            new Animal("Tweety", Animal.Type.BIRD, Animal.Sex.F, 101, 5, 0, false),
            new Animal("Jerry", Animal.Type.BIRD, Animal.Sex.M, 2, 10, 1, false),
            new Animal("Fluffy", Animal.Type.CAT, Animal.Sex.F, 4, 25, 4, false),
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, 5, 30, 5, false),
            new Animal("Spike", Animal.Type.DOG, Animal.Sex.M, 3, 40, 15, true)
        );

        assertEquals(expectedSortedAnimals, sortedAnimals);
    }
}
