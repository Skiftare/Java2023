package edu.hw4;

import java.util.List;

public class Task14 {
    public static boolean hasDogWithHeightAbove(List<Animal> animals, int height) {
        return animals.stream()
            .anyMatch(animal -> animal.getType() == Animal.Type.DOG && animal.getHeight() > height);
    }
}
