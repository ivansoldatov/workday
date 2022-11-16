package ru.vlbb.workday.repository;

import ru.vlbb.workday.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    Employee save(Employee employee);

    boolean delete(int id);

    Employee get(int id);

    List<Employee> getAll();
}