package ru.vlbb.workday.to;

import ru.vlbb.workday.model.AbstractBaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OperationTo extends AbstractBaseEntity {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final String description;
    private final boolean excess;
    private final Integer employeeId;

    public OperationTo(Integer id, LocalDateTime startDateTime, LocalDateTime endDateTime, String description, boolean excess, Integer employeeId) {
        super(id);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.excess = excess;
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public boolean isExcess() {
        return excess;
    }

    public Integer getEmployeeId() {return employeeId; }
}
