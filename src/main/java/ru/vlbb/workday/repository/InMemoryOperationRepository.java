package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.util.OperationUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryOperationRepository implements OperationRepository {

    private final Map<Integer, Operation> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        OperationUtil.operations.forEach(this::save);
    }

    @Override
    public Operation save(Operation operation) {
        if (operation.isNew()) {
            operation.setId(counter.getAndIncrement());
            repository.put(operation.getId(), operation);
            return operation;
        }
        return repository.computeIfPresent(operation.getId(), (id, oldOperation) -> operation);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Operation get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Operation> getAll() {
        return repository.values();
    }
}
