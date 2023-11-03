package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;


@SuppressWarnings("HideUtilityClassConstructor")
public class Task17 {
    public static boolean calculateAverageBites(@NotNull List<Animal> animals) {
        List<Animal> dogs = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG)
            .collect(Collectors.toList());

        List<Animal> spiders = animals.stream()
            .filter(animal -> animal.type() == Animal.Type.SPIDER)
            .collect(Collectors.toList());

        if (dogs.isEmpty() || spiders.isEmpty()) {
            return false;
        }

        double averageBitesDogs = dogs.stream()
            .filter(Animal::bites)
            .count() / (double) dogs.size();

        double averageBitesSpiders = spiders.stream()
            .filter(Animal::bites)
            .count() / (double) spiders.size();

        return averageBitesSpiders > averageBitesDogs;
    }
}
