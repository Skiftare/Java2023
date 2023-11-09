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
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );
        String averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("3ч 40м");
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
            "2022-03-12, 20:20 - 2022-03-12, 23:50"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("3ч 30м");
    }

    @Test
    public void testThatGetListOfManySessionsAndReturnedSum() {
        List<String> sessions = List.of(
            //"2022-03-12, 20:20 - 2022-03-12, 23:50"
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 23:20",
            "2022-05-15, 18:00 - 2022-05-15, 19:30"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("10ч 16м");
    }

    @Test
    public void testThatGetListOfManySessionsAndReturnedSumWithoutDays() {
        List<String> sessions = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 09:50",
            "2022-04-01, 21:30 - 2022-04-02, 03:20",
            "2022-05-15, 18:00 - 2022-05-15, 11:30"
        );
        String  averageSessionTime = computerClubAnalytics(sessions);
        assertThat(averageSessionTime).isEqualTo("-3ч -43м");
    }

    @Test
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
