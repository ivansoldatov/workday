package ru.vlbb.workday.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static ru.vlbb.workday.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.vlbb.workday.util.DateTimeUtil.atStartOfNextDayOrMax;
import static ru.vlbb.workday.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OperationService {

    private OperationRepository repository;

    OperationService(OperationRepository operationRepository) {
        this.repository = operationRepository;
    }

    public Operation create(Operation operation, int userId) {
        return repository.save(operation, userId);
    }

    public Operation update(Operation operation, int userId) {
        return checkNotFoundWithId(repository.save(operation, userId), operation.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Operation get(int id, int userId) {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Collection<Operation> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Operation> getBetweenInclusive(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate), userId);
    }
}