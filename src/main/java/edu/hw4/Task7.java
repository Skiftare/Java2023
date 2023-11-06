package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task7 {
    public static @NotNull Optional<Animal> findKthOldestAnimal(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::getAge).reversed())
            .skip(k - 1)
            .findFirst();
    }
}
