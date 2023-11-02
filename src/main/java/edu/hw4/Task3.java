package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {
    public static @NotNull Map<Animal.Type, Integer> countAnimalsByType(Animal @NotNull [] animals) {
        Map<Animal.Type, Integer> animalCount = new HashMap<>();
        for (Animal animal : animals) {
            animalCount.put(animal.getType(), animalCount.getOrDefault(animal.getType(), 0) + 1);
        }
        return animalCount;
    }
}
