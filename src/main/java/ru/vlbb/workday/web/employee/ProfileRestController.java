package ru.vlbb.workday.web.employee;

import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.Employee;

import static ru.vlbb.workday.web.SecurityUtil.authEmployeeId;

@Controller
public class ProfileRestController extends AbstractEmployeeController {

    public Employee get() {
        return super.get(authEmployeeId());
    }

    public void delete() {
        super.delete(authEmployeeId());
    }

    public void update(Employee employee) {
        super.update(employee, authEmployeeId());
    }
}