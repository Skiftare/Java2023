package edu.hw4;

import java.util.List;

public class Task12 {
    public static int countAnimalsWithWeightAboveHeight(List<Animal> animals) {
        return (int) animals.stream()
            .filter(animal -> animal.getWeight() > animal.getHeight())
            .count();
    }
}
