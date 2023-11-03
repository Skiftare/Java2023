package edu.hw4;

import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Task19 {
    public static Map<String, Set<ValidationError>> findInvalidAnimals(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !isValidName(animal.getName()) || !isValidAge(animal.getAge()))
            .collect(Collectors.toMap(
                Animal::name,
                Task19::getValidationErrors,
                Task19::mergeValidationErrors
            ));
    }

    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public static boolean isValidAge(int age) {
        return age < 100;
    }

    public static Set<ValidationError> getValidationErrors(Animal animal) {
        HashSet<ValidationError> errors = new HashSet<>();
        if (!isValidName(animal.name())) {
            errors.add(ValidationError.INVALID_NAME);
        }
        if (!isValidAge(animal.age())) {
            errors.add(ValidationError.INVALID_AGE);
        }
        return errors;
    }

    public static @NotNull Set<ValidationError> mergeValidationErrors(Set<ValidationError> errors1, Set<ValidationError> errors2) {
        Set<ValidationError> mergedErrors = new HashSet<>(errors1);
        mergedErrors.addAll(errors2);
        return mergedErrors;
    }

    public enum ValidationError {
        INVALID_NAME,
        INVALID_AGE
    }
}
