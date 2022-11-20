package ru.vlbb.workday.web.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vlbb.workday.model.User;
import ru.vlbb.workday.service.EmployeeService;

import java.util.List;

import static ru.vlbb.workday.util.ValidationUtil.assureIdConsistent;
import static ru.vlbb.workday.util.ValidationUtil.checkNew;

public abstract class AbstractEmployeeController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private EmployeeService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }
}