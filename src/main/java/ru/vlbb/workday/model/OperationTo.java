package ru.vlbb.workday.model;

import java.time.LocalDateTime;

public class OperationTo {
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

    public boolean isExcess() {
        return excess;
    }
}
