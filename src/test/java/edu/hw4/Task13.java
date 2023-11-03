package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task13 {
    public static List<Animal> findAnimalsWithMoreThanTwoWordsInName(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.getName().split(" ").length >= 2)
            .collect(Collectors.toList());
    }
}
