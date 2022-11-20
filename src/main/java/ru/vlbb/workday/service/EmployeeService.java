package ru.vlbb.workday.service;

import org.springframework.stereotype.Service;
import ru.vlbb.workday.model.User;
import ru.vlbb.workday.repository.UserRepository;

import java.util.List;


import static ru.vlbb.workday.util.ValidationUtil.checkNotFoundWithId;

@Service
public class EmployeeService {

    private UserRepository repository;

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}