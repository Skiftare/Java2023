package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {

    public static Map<Animal.Type, Animal> findHeaviestAnimalByType(@NotNull List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::getType,
                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Animal::getWeight)),
                    animal -> animal.orElse(null))));
    }
}
