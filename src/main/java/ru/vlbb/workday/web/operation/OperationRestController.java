package ru.vlbb.workday.web.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.service.OperationService;
import ru.vlbb.workday.to.OperationTo;
import ru.vlbb.workday.util.OperationUtil;
import ru.vlbb.workday.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static ru.vlbb.workday.util.ValidationUtil.assureIdConsistent;
import static ru.vlbb.workday.util.ValidationUtil.checkNew;
import static ru.vlbb.workday.web.SecurityUtil.authUserId;

@Controller
public class OperationRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final OperationService service;

    OperationRestController(OperationService service) {
        this.service = service;
    }

    public List<OperationTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return OperationUtil.getTos(service.getAll(userId), SecurityUtil.authUserMinutesPerDay());
    }

    public Operation get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get operation {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public Operation create(Operation operation) {
        int userId = SecurityUtil.authUserId();
        checkNew(operation);
        log.info("create {} for user {}", operation, userId);
        return service.create(operation, userId);
    }

    public void update(Operation operation, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(operation, id);
        log.info("update {} for user={}", operation, userId);
        service.update(operation, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete operation {} for user {}", id, userId);
        service.delete(id, userId);
    }



    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */

    public List<OperationTo> getBetween(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                        @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

        List<Operation> operationsDateFiltered = service.getBetweenInclusive(startDate, endDate, userId);
        return OperationUtil.getFilteredTos(operationsDateFiltered, SecurityUtil.authUserMinutesPerDay(), startTime, endTime);
    }
}