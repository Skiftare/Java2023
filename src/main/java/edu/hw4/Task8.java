package edu.hw4;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task8 {
    public static @NotNull Optional<Animal> findHeaviestAnimalBelowHeight(@NotNull List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.getHeight() < k)
            .max(Comparator.comparingInt(Animal::getWeight));
    }
}
