package ru.vlbb.workday.web;

import static ru.vlbb.workday.util.OperationUtil.DEFAULT_MINUTES_IN_DAY;

public class SecurityUtil {

    private static int id = 1;

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

    public static int authUserMinutesPerDay() {
        return DEFAULT_MINUTES_IN_DAY;
    }
}