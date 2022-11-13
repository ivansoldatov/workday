package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Operation;

import java.util.Collection;

public interface OperationRepository {
    // null if not found, when updated
    Operation save(Operation operation);

    // false if not found
    boolean delete(int id);

    // null if not found
    Operation get(int id);

    Collection<Operation> getAll();
}
