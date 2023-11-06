package edu.hw4;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task19 {

    @SuppressWarnings("MagicNumber")
    private static int maxAGE = 100;

    public static Map<String, Set<ValidationError>> findInvalidAnimals(@NotNull List<Animal> animals) {
        return animals.stream()
            .filter(animal -> !isValidName(animal.getName()) || !isValidAge(animal.getAge()))
            .collect(Collectors.toMap(
                Animal::name,
                Task19::getValidationErrors
            ));
    }

    @Contract(pure = true) public static boolean isValidName(@NotNull String name) {
        return name.matches("^[a-zA-Zа-яА-Я\s]+$");
    }

    public static boolean isValidAge(int age) {
        return age < maxAGE;
    }

    public static @NotNull Set<ValidationError> getValidationErrors(@NotNull Animal animal) {
        HashSet<ValidationError> errors = new HashSet<>();
        if (!isValidName(animal.name())) {
            errors.add(ValidationError.INVALID_NAME);
        }
        if (!isValidAge(animal.age())) {
            errors.add(ValidationError.INVALID_AGE);
        }
        return errors;
    }



    public enum ValidationError {
        INVALID_NAME,
        INVALID_AGE
    }
}
