package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    public void testThatGetYearAndReturnedNearestFriday13th() {
        int year = 1925;
        List<LocalDate> fridayThe13ths = Task2.findFridayThe13ths(year);
        assertThat(fridayThe13ths).containsExactly(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );
    }

    @Test
    public void testThatGetLeapYearAndReturnedNearestFriday13th() {
        int year = 2024;
        List<LocalDate> fridayThe13ths = Task2.findFridayThe13ths(year);
        assertThat(fridayThe13ths).containsExactly(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)
        );
    }

    @ParameterizedTest
    @CsvSource({
        "2023-10-20, 2024-09-13",
        "2022-05-13, 2023-01-13",
        "2023-11-25, 2024-09-13"
    })
    void testThatGetDateYearAndReturnedNearestFriday13th(LocalDate inputDate, LocalDate expectedDate) {
        LocalDate nextFridayThe13th = Task2.findNextFridayThe13th(inputDate);
        assertThat(nextFridayThe13th).isEqualTo(expectedDate);
    }
}
