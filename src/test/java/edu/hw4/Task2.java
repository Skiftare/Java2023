package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    public static List<Animal> sortAnimalsByWeightAndSelectTopK(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::getWeight).reversed())
            .limit(k)
            .collect(Collectors.toList());
    }
}
