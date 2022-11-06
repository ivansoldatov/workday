package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Operation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(Operation action, LocalTime startTime, LocalTime endTime) {
        return action.getStartTime().compareTo(startTime) >= 0 && action.getEndTime().compareTo(endTime) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}
