package ru.vlbb.workday.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.vlbb.workday.model.Employee;
import ru.vlbb.workday.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final Map<Integer, Employee> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Employee save(Employee employee) {
        if (employee.isNew()) {
            employee.setId(counter.getAndIncrement());
            repository.put(employee.getId(), employee);
            return employee;
        }
        return repository.computeIfPresent(employee.getId(), (id, oldEmployee) -> employee);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Employee get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(repository.values());
    }
}
