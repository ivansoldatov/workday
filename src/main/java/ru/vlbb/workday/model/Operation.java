package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Operation extends AbstractBaseEntity {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String description;

    public Operation(Integer id, LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        super(id);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
    }

    public Operation(LocalDateTime startDateTime, LocalDateTime endDateTime, String description) {
        this(null, startDateTime, endDateTime, description);
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDate getStartDate() {
        return startDateTime.toLocalDate();
    }

    public LocalTime getStartTime() {
        return startDateTime.toLocalTime();
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalDate getEndDate() {
        return endDateTime.toLocalDate();
    }

    public LocalTime getEndTime() {
        return endDateTime.toLocalTime();
    }

    public String getDescription() {
        return description;
    }

    public int getIntervalInMinutes() {
        long minutes = ChronoUnit.MINUTES.between(startDateTime, endDateTime);
//        Duration d = Duration.between(startDateTime, endDateTime);
//         return d.toMinutesPart();
        return (int) minutes;
//        double interval = (startDateTime.toEpochSecond(ZoneOffset.UTC) - endDateTime.toEpochSecond(ZoneOffset.UTC)) / 60 / 60.0;
//        return (Math.round(minutes/60.0) / 100.0);

    }
}

