package ru.vlbb.workday.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    static public DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}
