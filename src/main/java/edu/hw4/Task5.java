package edu.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class Task5 {
    public static Animal.@Nullable Sex findDominantSex(@NotNull List<Animal> animals) {
        int maleCount = 0;
        int femaleCount = 0;

        for (Animal animal : animals) {
            if (animal.getSex() == Animal.Sex.M) {
                maleCount++;
            } else if (animal.getSex() == Animal.Sex.F) {
                femaleCount++;
            }
        }

        if (maleCount > femaleCount) {
            return Animal.Sex.M;
        } else if (femaleCount > maleCount) {
            return Animal.Sex.F;
        } else {
            return null; // Если количество самцов и самок одинаково
        }
    }
}
