package edu.hw5;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class Task1 {

    Duration calculateDurationForString(String input){
        String[] parts = input.split(" - ");
        String start = parts[0];
        String end = parts[1];

        String[] startTime = start.split(", ")[1].split(":");
        String[] endTime = end.split(", ")[1].split(":");

        int startHour = Integer.parseInt(startTime[0]);
        int startMinute = Integer.parseInt(startTime[1]);
        int endHour = Integer.parseInt(endTime[0]);
        int endMinute = Integer.parseInt(endTime[1]);

        Duration intervalDuration = Duration.ofHours(endHour - startHour)
            .plusMinutes(endMinute - startMinute);
        return intervalDuration;
    }

    public String computerClubAnalytics(List<String> input){
        Duration totalDuration = Duration.ZERO;

        for (String interval : input) {
            totalDuration = totalDuration.plus(calculateDurationForString(interval));
        }

        Long hours = totalDuration.toHours();
        Long minutes = totalDuration.toMinutes() % 60;
        String res = hours + "ч " + minutes + "м";
        return res;
    }
}
