package ru.vlbb.workday.web.employee;

import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.User;

import static ru.vlbb.workday.web.SecurityUtil.authEmployeeId;

@Controller
public class ProfileRestController extends AbstractEmployeeController {

    public User get() {
        return super.get(authEmployeeId());
    }

    public void delete() {
        super.delete(authEmployeeId());
    }

    public void update(User user) {
        super.update(user, authEmployeeId());
    }
}