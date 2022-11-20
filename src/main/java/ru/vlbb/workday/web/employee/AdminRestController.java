package ru.vlbb.workday.web.employee;



import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.User;

import java.util.List;

@Controller
public class AdminRestController extends AbstractEmployeeController {

    @Override
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User create(User user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(User user, int id) {
        super.update(user, id);
    }

}