package edu.hw4;

import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task4 {
    public static Animal findAnimalWithLongestName(@NotNull List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.getName().length()))
            .orElse(null);
    }
}
