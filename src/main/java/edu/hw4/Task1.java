package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.List;

public class Task1 {


    public static @NotNull List<Animal> sortByHeight(@NotNull List<Animal> incomeAnimals) {

        incomeAnimals.sort(Comparator.comparingInt(Animal::getHeight));
        return incomeAnimals;
    }

}
