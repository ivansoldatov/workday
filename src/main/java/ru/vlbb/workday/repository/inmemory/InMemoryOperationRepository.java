package ru.vlbb.workday.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.vlbb.workday.util.ValidationUtil.belongToEmployeeId;

@Repository
public class InMemoryOperationRepository implements OperationRepository {

    private final Map<Integer, Operation> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        OperationUtil.operations.forEach(operation -> save(operation, 1));
    }

    @Override
    public Operation save(Operation operation, int employeeId) {
        if (operation.isNew()) {
            operation.setId(counter.getAndIncrement());
            repository.put(operation.getId(), operation);
            return operation;
        }
        return belongToEmployeeId(operation, employeeId) ? repository.computeIfPresent(operation.getId(), (id, oldOperation) -> operation) : null;
    }

    @Override
    public boolean delete(int id, int employeeId) {
        return belongToEmployeeId(repository.get(id), employeeId) && repository.remove(id) != null;
    }

    @Override
    public Operation get(int id, int employeeId) {
        return belongToEmployeeId(repository.get(id), employeeId) ? repository.get(id) : null;
    }

    @Override
    public Collection<Operation> getAll(int employeeId) {
        return repository.values().stream()
                .filter(operation -> operation.getEmployeeId() == employeeId)
                .sorted(Comparator.comparing(Operation::getStartDateTime).reversed())
                .collect(Collectors.toList());
//        return employees != null ? employees : new ArrayList<Operation>();
    }
}
