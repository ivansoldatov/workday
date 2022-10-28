package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Action {
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String description;

    public Action(LocalDate date, LocalTime startTime, LocalTime endTime, String description) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
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
