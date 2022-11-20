package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Operation;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface OperationRepository {
    // null if updated meal does not belong to userId
    Operation save(Operation operation, int employeeId);

    // false if meal does not belong to userId
    boolean delete(int id, int employeeId);

    /// null if updated meal does not belong to userId
    Operation get(int id, int employeeId);

    // ORDERED dateTime desc
    List<Operation> getAll(int employeeId);

    // ORDERED dateTime desc
    List<Operation> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int employeeId);
}
