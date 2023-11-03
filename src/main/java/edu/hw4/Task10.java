package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task10 {
    public static List<Animal> findAnimalsWithMismatchedAgeAndPaws(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.getAge() != animal.paws())
            .collect(Collectors.toList());
    }

}
