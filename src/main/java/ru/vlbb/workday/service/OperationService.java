package ru.vlbb.workday.service;

import org.springframework.stereotype.Service;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.OperationRepository;

import java.util.Collection;

import static ru.vlbb.workday.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OperationService {

    private OperationRepository repository;

    OperationService(OperationRepository operationRepository) {
        this.repository=operationRepository;
    }

    public Operation create(Operation operation, int employeeId) {
        return repository.save(operation, employeeId);
    }

    public Operation update(Operation operation, int employeeId) {
        return checkNotFoundWithId(repository.save(operation, employeeId), operation.getId());
    }

    public void delete(int id, int employeeId) {
        checkNotFoundWithId(repository.delete(id, employeeId), id);
    }

    public Operation get(int id, int employeeId) {
        return checkNotFoundWithId(repository.get(id, employeeId), id);
    }

    public Collection<Operation> getAll(int employeeId) {
        return repository.getAll(employeeId);
    }
}