package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task18 {
    public static Animal findHeaviestFish(List<List<Animal>> fishLists) {
        return fishLists.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }
}
