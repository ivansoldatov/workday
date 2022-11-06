package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActionTo {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String description;
    private final boolean excess;

    public ActionTo(LocalDate date, LocalTime startTime, LocalTime endTime, String description, boolean excess) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.excess = excess;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

    public boolean isExcess() { return excess; }
}
