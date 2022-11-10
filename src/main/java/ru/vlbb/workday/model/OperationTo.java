package ru.vlbb.workday.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OperationTo {
    private Integer id;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String description;
    private final boolean excess;

    public OperationTo(LocalDateTime startDateTime, LocalDateTime endDateTime, String description, boolean excess) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.excess = excess;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) { this.id = id;}

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

    public boolean isExcess() {
        return excess;
    }
}
