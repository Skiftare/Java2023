package edu.hw5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class Task3Test {
    @Test
    public void testThatGetDateByNumbersAndReturnedParsedDate() {
        // Проверка корректного парсинга дат
        Assertions.assertThat(Task3.parseDate("2023-11-07").get()).isEqualTo(LocalDate.of(2023, 11, 7));
        Assertions.assertThat(Task3.parseDate("11/7/2023").get()).isEqualTo(LocalDate.of(2023, 11, 7));
        Assertions.assertThat(Task3.parseDate("11/7/23").get()).isEqualTo(LocalDate.of(2023, 11, 7));

    }
    @Test
    public void testThatGetDateByWordsAndReturnedParsedDate() {
        // Проверка парсинга специальных слов
        Assertions.assertThat(Task3.parseDate("today").get()).isEqualTo(LocalDate.now());
        Assertions.assertThat(Task3.parseDate("tomorrow").get()).isEqualTo(LocalDate.now().plusDays(1));
        Assertions.assertThat(Task3.parseDate("yesterday").get()).isEqualTo(LocalDate.now().minusDays(1));
    }
    @Test
    public void testThatGetDateByNumbersAndWordsAndReturnedParsedDate() {
        // Проверка парсинга дат, указанных через несколько дней назад
        Assertions.assertThat(Task3.parseDate("2 days ago").get()).isEqualTo(LocalDate.now().minusDays(2));
        Assertions.assertThat(Task3.parseDate("5 days ago").get()).isEqualTo(LocalDate.now().minusDays(5));
    }
    @Test
    public void testThatGetInvalidAndReturnedInvalidDate() {

        // Проверка некорректного ввода
        Assertions.assertThat(Task3.parseDate("invalid date")).isEmpty();
    }
}
