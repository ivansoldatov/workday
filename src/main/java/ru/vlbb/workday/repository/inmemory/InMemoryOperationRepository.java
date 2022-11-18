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
        return repository.computeIfPresent(operation.getId(), (id, oldOperation) -> operation);
    }

    @Override
    public boolean delete(int id, int employeeId) {
        return repository.remove(id) != null;
    }

    @Override
    public Operation get(int id, int employeeId) {
        return repository.get(id);
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
