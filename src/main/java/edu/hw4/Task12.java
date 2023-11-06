package edu.hw4;

import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task12 {
    public static int countAnimalsWithWeightAboveHeight(@NotNull List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.getWeight() > animal.getHeight())
            .count();
    }
}
