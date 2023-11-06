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
    public void testThatGetMixedListOfAnimalsAndReturnedMapOfInvalidAnimalsErrors() {
        // Arrange
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Act
        Map<String, Set<Task19.ValidationError>> invalidAnimals = Task19.findInvalidAnimals(animals);
        //System.out.println(invalidAnimals);
        // Assert
        assertEquals(3, invalidAnimals.size());


        assertTrue(invalidAnimals.get("Tweety(X)").contains(Task19.ValidationError.INVALID_NAME));
        assertTrue(invalidAnimals.get("Кот1").contains(Task19.ValidationError.INVALID_NAME));
        assertTrue(invalidAnimals.get("Tweety").contains(Task19.ValidationError.INVALID_AGE));
    }
}
