package edu.hw4;

import java.util.List;
import java.util.stream.Collectors;

public class Task11 {
    public static List<Animal> findAnimalsWithBitesAndHeightAbove(List<Animal> animals, int incomeHeight) {
        return animals.stream()
            .filter(animal -> animal.getBites() && animal.getHeight() > incomeHeight)
            .collect(Collectors.toList());
    }
}
