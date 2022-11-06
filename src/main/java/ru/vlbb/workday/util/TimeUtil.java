package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Action;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    static public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean isBetweenHalfOpen(Action action, LocalTime startTime, LocalTime endTime) {
        return action.getStartTime().compareTo(startTime) >= 0 && action.getEndTime().compareTo(endTime) < 0;
    }
}
