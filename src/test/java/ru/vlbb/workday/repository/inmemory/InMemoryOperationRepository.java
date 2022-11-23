package ru.vlbb.workday.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;
import ru.vlbb.workday.util.OperationUtil;
import ru.vlbb.workday.util.Util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.vlbb.workday.UserTestData.ADMIN_ID;
import static ru.vlbb.workday.UserTestData.USER_ID;

@Repository
public class InMemoryOperationRepository implements OperationRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryOperationRepository.class);

    // Map  userId -> OperationRepository
    private final Map<Integer, InMemoryBaseRepository<Operation>> usersOperationsMap = new ConcurrentHashMap<>();

    {
        OperationUtil.operations.forEach(operation -> save(operation, USER_ID));
        save(new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 9, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 11, 5), "Работа с клиентом"),ADMIN_ID);
        save(new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 12, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 17, 0), "Подготовка отчета для проверки ЦБ"),ADMIN_ID);
    }

    @Override
    public Operation save(Operation operation, int userId) {
        InMemoryBaseRepository<Operation> operations = usersOperationsMap.computeIfAbsent(userId, uId -> new InMemoryBaseRepository<>());
        return operations.save(operation);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Operation> operations = usersOperationsMap.get(userId);
        return operations != null && operations.delete(id);
    }

    @Override
    public Operation get(int id, int userId) {
        InMemoryBaseRepository<Operation> operations = usersOperationsMap.get(userId);
        return operations == null ? null : operations.get(id);
    }

    @Override
    public List<Operation> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return filterByPredicate(userId, operation -> Util.isBetweenHalfOpen(operation.getStartDateTime(), startDateTime, endDateTime));
    }

    @Override
    public List<Operation> getAll(int userId) {
        return filterByPredicate(userId, operation -> true);
    }

    private List<Operation> filterByPredicate(int userId, Predicate<Operation> filter) {
        InMemoryBaseRepository<Operation> operations = usersOperationsMap.get(userId);
        return operations == null ? Collections.emptyList() :
                operations.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Operation::getStartDateTime).reversed())
                        .collect(Collectors.toList());
    }
}