package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    @Test
    @DisplayName("Тест на получение id тор-историй")
    void testThatGetTopStoriesAndReturnedTheirSize() {
        long[] topStories = Task5.hackerNewsTopStories();
        assertEquals(387, topStories.length);
    }

    @Test
    @DisplayName("Тест на получение заголовка по id")
    void testThatGetStoryIdAndReturnedTitle() {
        String newsTitle = Task5.news(37570037);
        assertEquals("JDK 21 Release Notes", newsTitle);
    }
}
