package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Operation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH-mm");

    public static String dateToString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_FORMATTER);
    }

    public static String timeToString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(TIME_FORMATTER);
    }
}
