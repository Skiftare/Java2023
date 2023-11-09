package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import static edu.hw5.Task1.computerClubAnalytics;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    @DisplayName("Тест на базовом примере")
    public void testThatGetListOfSessionsAndReturnedSumOfTimes() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        String averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("3ч 40м");
    }

    @Test
    @DisplayName("Тест на пустом листе входных данных")
    public void testThatGetEmptyListOfSessionsAndReturnedZero() {
        List<String> sessions = List.of();
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("0ч 0м");
    }

    @Test
    @DisplayName("Тест на входных данных из 1 сессии")
    public void testThatGetListOfOneSessionAndReturnedSessionTime() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("3ч 30м");
    }

    @Test
    @DisplayName("Тест на входных данных из нескольких сессий")
    public void testThatGetListOfManySessionsAndReturnedSum() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 23:20",
            "2022-05-15, 18:00 - 2022-05-15, 19:30"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("10ч 16м");
    }

    @Test
    @DisplayName("Тест на некорректных входных данных (отрицательное время) из нескольких сессий")
    public void testThatGetListOfManySessionsAndReturnedNegativeSum() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 09:50",
            "2022-04-01, 21:30 - 2022-04-02, 03:20",
            "2022-05-15, 18:00 - 2022-05-15, 11:30"
        );
        Throwable ex = assertThrows(RuntimeException.class,()-> computerClubAnalytics(sessions));
        assertEquals(ex.getMessage(), "Wrong time format");
    }

    @Test
    @DisplayName("Тест на некорректных входных данных (неправильный формат) из нескольких сессий")
    public void testThatGetListOfManySessionsAndReturnedException() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 09:50",
            "2022-04-01, 21:30:00 - 2022-04-02, 03:230",
            "2022-05-15, 18:00:00 - 2022-05-15, 411:30"
        );

        Throwable ex = assertThrows(RuntimeException.class,()-> computerClubAnalytics(sessions));
        assertEquals(ex.getMessage(), "Wrong time format");
    }
}
