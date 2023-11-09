package edu.hw5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class Task3Test {
    @Test
    @DisplayName("Проверка парсинга дат в стандартном формате")
    public void testThatGetDateByNumbersAndReturnedParsedDate() {
        Assertions.assertThat(Task3.parseDate("2023-11-07").get()).isEqualTo(LocalDate.of(2023, 11, 7));
        Assertions.assertThat(Task3.parseDate("11/7/2023").get()).isEqualTo(LocalDate.of(2023, 11, 7));
        Assertions.assertThat(Task3.parseDate("11/7/23").get()).isEqualTo(LocalDate.of(2023, 11, 7));

    }
    @Test
    @DisplayName("Проверка парсинга разговорных слов, обозначающих время")
    public void testThatGetDateByWordsAndReturnedParsedDate() {
        Assertions.assertThat(Task3.parseDate("today").get()).isEqualTo(LocalDate.now());
        Assertions.assertThat(Task3.parseDate("tomorrow").get()).isEqualTo(LocalDate.now().plusDays(1));
        Assertions.assertThat(Task3.parseDate("yesterday").get()).isEqualTo(LocalDate.now().minusDays(1));
    }
    @Test
    @DisplayName("Проверка корректного парсинга дат, заданных относительно текущей")
    public void testThatGetDateByNumbersAndWordsAndReturnedParsedDate() {
        Assertions.assertThat(Task3.parseDate("2 days ago").get()).isEqualTo(LocalDate.now().minusDays(2));
        Assertions.assertThat(Task3.parseDate("5 days ago").get()).isEqualTo(LocalDate.now().minusDays(5));
    }
    @Test
    @DisplayName("Проверка ошибки парсинга дат на неучтённом формате")
    public void testThatGetInvalidAndReturnedInvalidDate() {
        Assertions.assertThat(Task3.parseDate("invalid date")).isEmpty();
    }
}
