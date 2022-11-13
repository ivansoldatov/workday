package ru.vlbb.workday.web.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vlbb.workday.model.Employee;
import ru.vlbb.workday.service.EmployeeService;

import java.util.List;

import static ru.vlbb.workday.util.ValidationUtil.assureIdConsistent;
import static ru.vlbb.workday.util.ValidationUtil.checkNew;

public abstract class AbstractEmployeeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private EmployeeService service;

    public List<Employee> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Employee get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Employee create(Employee employee) {
        log.info("create {}", employee);
        checkNew(employee);
        return service.create(employee);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Employee employee, int id) {
        log.info("update {} with id={}", employee, id);
        assureIdConsistent(employee, id);
        service.update(employee);
    }

    public Employee getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}