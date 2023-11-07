package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;
import static edu.hw5.Task1.computerClubAnalytics;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    @Test
    public void testThatGetListOfSessionsAndReturnedSumOfTimes() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 23:50:00",
            "2022-04-01, 21:30:00 - 2022-04-02, 22:20:00"
        );
        String averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("4ч 20м");
    }

    @Test
    public void testThatGetEmptyListOfSessionsAndReturnedZero() {
        List<String> sessions = List.of();
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("0ч 0м");
    }

    @Test
    public void testThatGetListOfOneSessionAndReturnedSessionTime() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 23:50:00"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("3ч 30м");
    }

    @Test
    public void testThatGetListOfManySessionsAndReturnedSum() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 23:50:00",
            "2022-04-01, 21:30:00 - 2022-04-02, 23:20:00",
            "2022-05-15, 18:00:00 - 2022-05-15, 19:30:00"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("6ч 50м");
    }

    @Test
    public void testThatGetListOfManySessionsAndReturnedSumWithoutDays() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 09:50:00",
            "2022-04-01, 21:30:00 - 2022-04-02, 03:20:00",
            "2022-05-15, 18:00:00 - 2022-05-15, 11:30:00"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("-35ч -10м");
    }

    @Test
    public void testThatGetListOfManySessionsAndReturnedException() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20:00 - 2022-03-12, 09:50:004",
            "2022-04-01, 21:30:00 - 2022-04-02, 03:230:00",
            "2022-05-15, 18:00:00 - 2022-05-15, 411:30:00"
        );

        Throwable ex = assertThrows(RuntimeException.class,()-> computerClubAnalytics(sessions));
        assertEquals(ex.getMessage(), "Wrong time format");
    }
}
