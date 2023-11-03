package edu.hw4;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task16 {
    public static List<Animal> sortAnimals(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }
}
