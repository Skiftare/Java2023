package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.hw4.Task19.isValidAge;
import static edu.hw4.Task19.isValidName;

public class Task20 {
    public static Map<String, String> findInvalidAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !isValidName(animal.getName()) || !isValidAge(animal.age()))
            .collect(Collectors.toMap(
                Animal::name,
                Task20::getValidationErrorsAsString
            ));
    }
    private static String getValidationErrorsAsString(Animal animal) {
        List<String> errorFields = new ArrayList<>();
        if (!isValidName(animal.name())) {
            errorFields.add("name");
        }
        if (!isValidAge(animal.age())) {
            errorFields.add("age");
        }
        return String.join(", ", errorFields);
    }

}
