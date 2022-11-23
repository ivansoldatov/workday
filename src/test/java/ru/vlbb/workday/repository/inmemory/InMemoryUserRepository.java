package ru.vlbb.workday.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.vlbb.workday.UserTestData;
import ru.vlbb.workday.model.User;
import ru.vlbb.workday.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.vlbb.workday.UserTestData.*;

@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        put(user);
        put(admin);
        put(guest);
        counter.getAndSet(UserTestData.GUEST_ID + 1);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }
/*
    @Override
    public User getByEmail(String email) {
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    } */

}

