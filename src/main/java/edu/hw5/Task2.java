package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task2 {
    private static final int MAX_MONTH = 12;
    private static final int NEED_DAY = 13;

    public static @NotNull List<LocalDate> findFridayThe13ths(int year) {
        List<LocalDate> fridayThe13ths = new ArrayList<>();
        for (int month = 1; month <= MAX_MONTH; month++) {
            LocalDate date = LocalDate.of(year, month, NEED_DAY);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridayThe13ths.add(date);
            }
        }
        return fridayThe13ths;
    }

    public static LocalDate findNextFridayThe13th(LocalDate date) {
        LocalDate res = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)).withDayOfMonth(NEED_DAY);
        while (res.isBefore(date) || res.isEqual(date) || res.getDayOfWeek() != DayOfWeek.FRIDAY) {
            res = findNextFridayThe13th(date.plusDays(1));
        }
        return res;
    }
}
