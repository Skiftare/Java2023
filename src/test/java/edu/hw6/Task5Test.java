package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {
    @Test
    @DisplayName("Тест на получение id тор-историй")
    void testThatGetTopStoriesAndReturnedTheirSize() {
        //Given: request for check site
        //When: make attempt to parse the file
        long[] topStories = Task5.hackerNewsTopStories();
        //Then: check for full parse
        assertEquals(472, topStories.length);
    }

    @Test
    @DisplayName("Тест на получение заголовка по id")

    void testThatGetStoryIdAndReturnedTitle() {
        //Given: request for check site
        //When: make attempt to parse the news by id
        String newsTitle = Task5.news(37570037);
        //Then: check for correct parsing of title
        assertEquals("JDK 21 Release Notes", newsTitle);
    }
}
