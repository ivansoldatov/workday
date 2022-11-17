package ru.vlbb.workday.web.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.service.OperationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.vlbb.workday.util.ValidationUtil.assureIdConsistent;
import static ru.vlbb.workday.util.ValidationUtil.checkNew;
import static ru.vlbb.workday.web.SecurityUtil.authEmployeeId;

@Controller
public class OperationRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private OperationService service;

    public List<Operation> getAll() {
        log.info("getAll");
        return new ArrayList<>(service.getAll(authEmployeeId()));
    }

    public Operation get(int id) {
        log.info("get {}", id);
        return service.get(id, authEmployeeId());
    }

    public Operation create(Operation operation) {
        log.info("create {}", operation);
        checkNew(operation);
        return service.create(operation, authEmployeeId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authEmployeeId());
    }

    public void update(Operation operation, int id) {
        log.info("update {} with id={}", operation, id);
        assureIdConsistent(operation, id);
        service.update(operation, authEmployeeId());
    }
}