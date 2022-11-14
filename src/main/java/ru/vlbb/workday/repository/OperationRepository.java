package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Operation;

import java.util.Collection;

public interface OperationRepository {
    // null if not found, when updated
    Operation save(Operation operation, Integer employeeId);

    // false if not found
    boolean delete(int id, Integer employeeId);

    // null if not found
    Operation get(int id, Integer employeeId);

    Collection<Operation> getAll(Integer employeeId);
}
