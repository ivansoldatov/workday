package ru.vlbb.workday;

import ru.vlbb.workday.model.Role;
import ru.vlbb.workday.model.User;

public class UserTestData {
    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 2;
    public static final int GUEST_ID = 3;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "Солдатов Иван", "User", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Симонова Дина", "Admin", "admin", Role.ADMIN);
    public static final User guest = new User(GUEST_ID, "Костин Алексей", "Guest", "guest");
}
