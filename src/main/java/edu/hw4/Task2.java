package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static java.lang.Math.min;

public class Task2 {
    public static @NotNull List<Animal> sortByWeightWithK(@NotNull List<Animal> incomeAnimals, int k) {

        incomeAnimals.sort(Comparator.comparingInt(Animal::getWeight));
        List<Animal> resultAnimals = new ArrayList<>();
        Collections.reverse(incomeAnimals);
        for(int i = 0;i<min(k, incomeAnimals.size());i++){
            resultAnimals.add(incomeAnimals.get(i));
        }

        return resultAnimals;
    }
}
