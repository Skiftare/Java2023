package edu.hw4;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task20Test {

    @Test
    @DisplayName("Тест нахождения невалидных животных")
    public void testThatGetEngListOfAnimalsAndReturnedPrettyMapOfInvalidAnimalsErrors() {
        // Arrange
        List<Animal> animals = ForAllTestsPatterns.getEngArray();

        // Act
        Map<String, String> invalidAnimals = Task20.findInvalidAnimals(animals);

        // Assert
        assertEquals(1, invalidAnimals.size());
        assertTrue(invalidAnimals.containsKey("Tweety"));
        assertEquals("age", invalidAnimals.get("Tweety"));
    }

    @Test
    @DisplayName("Тест нахождения невалидных животных смешанного массива")
    public void testThatGetMixedListOfAnimalsAndReturnedPrettyMapOfInvalidAnimalsErrors() {
        // Arrange
        List<Animal> animals = ForAllTestsPatterns.getMixedArray();

        // Act
        Map<String, String> invalidAnimals = Task20.findInvalidAnimals(animals);

        // Assert
        assertEquals(3, invalidAnimals.size());
        assertEquals("name", invalidAnimals.get("Tweety(X)"));
        assertEquals("name", invalidAnimals.get("Кот1"));
        assertEquals("age", invalidAnimals.get("Tweety"));
    }
}
