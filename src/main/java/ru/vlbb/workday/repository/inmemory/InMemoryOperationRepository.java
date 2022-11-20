package ru.vlbb.workday.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;
import ru.vlbb.workday.util.Util;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryOperationRepository implements OperationRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final Map<Integer, Map<Integer, Operation>> userOperationsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        OperationUtil.operations.forEach(operation -> save(operation, 1));
    }

    @Override
    public Operation save(Operation operation, int employeeId) {
        Map<Integer, Operation> operations = userOperationsMap.computeIfAbsent(employeeId, eId -> new ConcurrentHashMap<>());
        if (operation.isNew()) {
            operation.setId(counter.incrementAndGet());
            operations.put(operation.getId(), operation);
            return operation;
        }
        return operations.computeIfPresent(operation.getId(), (id, oldOperation) -> operation);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Operation> operations = userOperationsMap.get(userId);
        return operations != null && operations.remove(id) != null;
    }

    @Override
    public Operation get(int id, int userId) {
        Map<Integer, Operation> operations = userOperationsMap.get(userId);
        return operations == null ? null : operations.get(id);
    }


    @Override
    public List<Operation> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, operation -> Util.isBetweenHalfOpen(operation.getStartDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Operation> getAll(int userId) {
        return filterByPredicate(userId, meal -> true);
    }

    private List<Operation> filterByPredicate(int userId, Predicate<Operation> filter) {
        Map<Integer, Operation> operations = userOperationsMap.get(userId);
        return CollectionUtils.isEmpty(operations) ? Collections.emptyList() :
                operations.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Operation::getStartDate).reversed())
                        .collect(Collectors.toList());
    }
}
