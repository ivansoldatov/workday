package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActionTo {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final int duration;
    private final String description;
    private final boolean excess;

    public ActionTo(LocalDate date, LocalTime startTime, LocalTime endTime, int duration, String description, boolean excess) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
