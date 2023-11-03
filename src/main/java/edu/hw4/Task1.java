package edu.hw4;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task1 {

    public static List<Animal> sortAnimalsByHeight(@NotNull List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::getHeight))
            .collect(Collectors.toList());
    }

}
