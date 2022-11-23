package ru.vlbb.workday.web.employee;

import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.User;

import static ru.vlbb.workday.web.SecurityUtil.authUserId;

@Controller
public class ProfileRestController extends AbstractUserController {

    public User get() {
        return super.get(authUserId());
    }

    public void delete() {
        super.delete(authUserId());
    }

    public void update(User user) {
        super.update(user, authUserId());
    }
}