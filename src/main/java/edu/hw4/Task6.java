package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task6 {
    public static @NotNull Map<Animal.Type, Animal> findHeaviestAnimalByType(@NotNull List<Animal> animals) {
        Map<Animal.Type, Animal> heaviestAnimals = new HashMap<>();

        for (Animal animal : animals) {
            Animal currentHeaviest = heaviestAnimals.get(animal.getType());
            if (currentHeaviest == null || animal.getWeight() > currentHeaviest.getWeight()) {
                heaviestAnimals.put(animal.getType(), animal);
            }
        }

        return heaviestAnimals;
    }
}
