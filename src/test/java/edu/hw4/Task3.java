package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task3 {
    public static Map<Animal.Type, Integer> countAnimalsByType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::getType, Collectors.summingInt(animal -> 1)));
    }
}
