package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Operation;

import java.util.Collection;

public interface OperationRepository {
    // null if updated meal does not belong to userId
    Operation save(Operation operation, int employeeId);

    // false if meal does not belong to userId
    boolean delete(int id, int employeeId);

    /// null if updated meal does not belong to userId
    Operation get(int id, int employeeId);

    // ORDERED dateTime desc
    Collection<Operation> getAll(int employeeId);
}
