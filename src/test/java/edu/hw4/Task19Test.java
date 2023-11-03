package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task19Test {

    @Test
    @DisplayName("Тест нахождения невалидных животных")
    public void testFindInvalidAnimals() {
        // Arrange
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Act
        Map<String, Set<Task19.ValidationError>> invalidAnimals = Task19.findInvalidAnimals(animals);

        // Assert
        assertEquals(1, invalidAnimals.size());
        assertTrue(invalidAnimals.containsKey("Tweety"));
        Set<Task19.ValidationError> errors = invalidAnimals.get("Tweety");
        assertTrue(errors.contains(Task19.ValidationError.INVALID_AGE));
    }

    @Test
    @DisplayName("Тест нахождения невалидных животных смешанного массива")
    public void testFindInvalidAnimalsMixedArray() {
        // Arrange
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Act
        Map<String, Set<Task19.ValidationError>> invalidAnimals = Task19.findInvalidAnimals(animals);
        System.out.println(invalidAnimals);
        // Assert
        assertEquals(2, invalidAnimals.size());
        assertTrue(invalidAnimals.containsKey("Tweety(X)"));
        assertTrue(invalidAnimals.containsKey("Кот1"));
        Set<Task19.ValidationError> errors1 = invalidAnimals.get("Tweety(X)");
        Set<Task19.ValidationError> errors2 = invalidAnimals.get("Кот1");
        assertTrue(errors1.contains(Task19.ValidationError.INVALID_NAME));
        assertTrue(errors2.contains(Task19.ValidationError.INVALID_NAME));
    }
}
