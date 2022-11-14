package ru.vlbb.workday.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryOperationRepository implements OperationRepository {

    private final Map<Integer, Operation> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        OperationUtil.operations.forEach(operation -> save(operation, 1));
    }

    @Override
    public Operation save(Operation operation, Integer employeeId) {
        if (operation.isNew()) {
            operation.setId(counter.getAndIncrement());
            repository.put(operation.getId(), operation);
            return operation;
        }
        return repository.computeIfPresent(operation.getId(), (id, oldOperation) -> operation);
    }

    @Override
    public boolean delete(int id, Integer employeeId) {
        return repository.remove(id) != null;
    }

    @Override
    public Operation get(int id, Integer employeeId) {
        return repository.get(id);
    }

    @Override
    public Collection<Operation> getAll(Integer employeeId) {
        return repository.values();
    }
}
