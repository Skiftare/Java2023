package edu.hw4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task5 {
    public static Animal.Sex findDominantSex(@NotNull List<Animal> animals) {
        Map<Animal.Sex, Long> sexCounts = animals.stream()
            .collect(Collectors.groupingBy(Animal::getSex, Collectors.counting()));

        return sexCounts.entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
    }
}
