package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Employee;

import java.util.List;

public interface EmployeeRepository {
    // null if not found, when updated
    Employee save(Employee employee);

    // false if not found
    boolean delete(int id);

    // null if not found
    Employee get(int id);

    // null if not found
    Employee getByEmail(String email);

    List<Employee> getAll();
}