package ru.vlbb.workday.service;

import org.springframework.stereotype.Service;
import ru.vlbb.workday.model.Employee;
import ru.vlbb.workday.repository.EmployeeRepository;

import java.util.List;


import static ru.vlbb.workday.util.ValidationUtil.checkNotFoundWithId;

@Service
public class EmployeeService {

    private EmployeeRepository repository;

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Employee get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Employee> getAll() {
        return repository.getAll();
    }

    public void update(Employee employee) {
        checkNotFoundWithId(repository.save(employee), employee.getId());
    }
}