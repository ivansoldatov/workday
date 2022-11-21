package ru.vlbb.workday.web;

import static ru.vlbb.workday.util.OperationUtil.DEFAULT_MINUTES_IN_DAY;

public class SecurityUtil {

    public static int authUserId() {
        return 1;
    }

    public static int authUserMinutesPerDay() {
        return DEFAULT_MINUTES_IN_DAY;
    }
}