package ru.vlbb.workday.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;
import ru.vlbb.workday.util.Util;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.vlbb.workday.repository.inmemory.InMemoryUserRepository.ADMIN_ID;
import static ru.vlbb.workday.repository.inmemory.InMemoryUserRepository.USER_ID;

@Repository
public class InMemoryOperationRepository implements OperationRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final Map<Integer, InMemoryBaseRepository<Operation>> usersOperationsMap = new ConcurrentHashMap<>();

    {
        OperationUtil.operations.forEach(Operation -> save(Operation, USER_ID));
        save(new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 9, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 11, 5), "Работа с клиентом"), ADMIN_ID);
        save(new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 12, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 17, 0), "Подготовка отчета для проверки ЦБ"), ADMIN_ID);
    }

    @Override
    public Operation save(Operation Operation, int userId) {
        InMemoryBaseRepository<Operation> Operations = usersOperationsMap.computeIfAbsent(userId, uId -> new InMemoryBaseRepository<>());
        return Operations.save(Operation);
    }

    @Override
    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Operation> Operations = usersOperationsMap.get(userId);
        return Operations != null && Operations.delete(id);
    }

    @Override
    public Operation get(int id, int userId) {
        InMemoryBaseRepository<Operation> Operations = usersOperationsMap.get(userId);
        return Operations == null ? null : Operations.get(id);
    }

    @Override
    public List<Operation> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, Operation -> Util.isBetweenHalfOpen(Operation.getStartDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Operation> getAll(int userId) {
        return filterByPredicate(userId, Operation -> true);
    }

    private List<Operation> filterByPredicate(int userId, Predicate<Operation> filter) {
        InMemoryBaseRepository<Operation> Operations = usersOperationsMap.get(userId);
        return Operations == null ? Collections.emptyList() :
                Operations.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Operation::getStartDateTime).reversed())
                        .collect(Collectors.toList());
    }
}
