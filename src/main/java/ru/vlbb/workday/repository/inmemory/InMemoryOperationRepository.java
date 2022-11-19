package ru.vlbb.workday.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryOperationRepository implements OperationRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryEmployeeRepository.class);

    private final Map<Integer, Map<Integer, Operation>> employeeOperationsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        OperationUtil.operations.forEach(operation -> save(operation, 1));
    }

    @Override
    public Operation save(Operation operation, int employeeId) {
        Map<Integer, Operation> operations = employeeOperationsMap.computeIfAbsent(employeeId, eId -> new ConcurrentHashMap<>());
        if (operation.isNew()) {
            operation.setId(counter.incrementAndGet());
            operations.put(operation.getId(), operation);
            return operation;
        }
        return operations.computeIfPresent(operation.getId(), (id, oldOperation) -> operation);
    }

    @Override
    public boolean delete(int id, int employeeId) {
        Map<Integer, Operation> operations = employeeOperationsMap.get(employeeId);
        return operations != null && operations.remove(id) != null;
    }

    @Override
    public Operation get(int id, int employeeId) {
        Map<Integer, Operation> operations = employeeOperationsMap.get(employeeId);
        return operations == null ? null : operations.get(id);
    }

    @Override
    public Collection<Operation> getAll(int employeeId) {
        Map<Integer, Operation> operations = employeeOperationsMap.get(employeeId);
        return operations.values().stream()
                .sorted(Comparator.comparing(Operation::getStartDateTime).reversed())
                .collect(Collectors.toList());
    }
}
