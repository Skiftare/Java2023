package edu.hw4;

import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task14 {
    public static boolean hasDogWithHeightAbove(@NotNull List<Animal> animals, int height) {
        return animals.stream()
            .anyMatch(animal -> animal.getType() == Animal.Type.DOG && animal.getHeight() > height);
    }
}
