package ru.vlbb.workday.model;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Operation extends AbstractBaseEntity {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String description;
    private Integer employeeId;

    public Operation(Integer id, LocalDateTime startDateTime, LocalDateTime endDateTime, String description, Integer employeeId) {
        super(id);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.employeeId = employeeId;
    }

    public Operation(LocalDateTime startDateTime, LocalDateTime endDateTime, String description, Integer employeeId) {
        this(null, startDateTime, endDateTime, description, employeeId);
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

    public Integer getEmployeeId() { return employeeId; }

    public int getIntervalInMinutes() {
        long minutes= ChronoUnit.MINUTES.between(startDateTime,endDateTime);
//        Duration d = Duration.between(startDateTime, endDateTime);
//         return d.toMinutesPart();
        return (int)minutes;
//        double interval = (startDateTime.toEpochSecond(ZoneOffset.UTC) - endDateTime.toEpochSecond(ZoneOffset.UTC)) / 60 / 60.0;
//        return (Math.round(minutes/60.0) / 100.0);

    }
}

