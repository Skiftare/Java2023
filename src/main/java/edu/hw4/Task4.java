package edu.hw4;

import java.util.List;

public class Task4 {
    public static Animal findAnimalWithLongestName(List<Animal> animals) {
        Animal animalWithLongestName = null;
        int maxLength = 0;

        for (Animal animal : animals) {
            if (animal.getName().length() > maxLength) {
                maxLength = animal.getName().length();
                animalWithLongestName = animal;
            }
        }

        return animalWithLongestName;
    }
}
