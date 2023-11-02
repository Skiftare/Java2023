package edu.hw4;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;

public class Task7 {
    public static @Nullable Animal findOldestAnimal(@NotNull List<Animal> animals, int k) {
        animals.sort((a1, a2) -> Integer.compare(a2.getAge(), a1.getAge())); // Сортировка по убыванию возраста

        if (k >= 1 && k <= animals.size()) {
            return animals.get(k - 1); // Возвращаем k-е самое старое животное (с индексом k-1)
        } else {
            return null; // Если k выходит за пределы списка животных
        }
    }
}
