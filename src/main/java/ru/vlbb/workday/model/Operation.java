package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Locale;

public class Operation {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String description;

    public Operation(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }
    public LocalDate getStartDate() {return startDateTime.toLocalDate();}
    public LocalTime getStartTime() {return startDateTime.toLocalTime();}

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }
    public LocalDate getEndDate() {return  endDateTime.toLocalDate();}
    public LocalTime getEndTime() {return endDateTime.toLocalTime();}

    public String getDescription() {
        return description;
    }

    public double getIntervalInHours() {
        double interval = (startDateTime.toEpochSecond(ZoneOffset.UTC) - endDateTime.toEpochSecond(ZoneOffset.UTC)) / 60 / 60.0;
        return (Math.round(interval * 100.0) / 100.0);
    }
}