package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Action;
import ru.vlbb.workday.model.ActionTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static ru.vlbb.workday.util.TimeUtil.dtf;

public class ActionsUtil {

    public static void main(String[] args) {
        List<Action> actions = Arrays.asList(
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(8, 15), LocalTime.of(8, 30), "Подготовка рабочего места"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(9, 0), LocalTime.of(10, 0), "Подготовка договора для клиента"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(10, 15), LocalTime.of(11, 45), "Работа с клиентом"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(12, 0), LocalTime.of(13, 0), "Перерыв"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(8, 15), LocalTime.of(8, 30), "Подготовка рабочего места"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(9, 0), LocalTime.of(10, 0), "Подготовка договора для клиента"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(10, 15), LocalTime.of(11, 45), "Работа с клиентом"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(13, 0), LocalTime.of(17, 0), "Перерыв")
        );
    }

    public static List<ActionTo> filteredByStreams(List<Action> actions, LocalDate date, LocalTime startTime, LocalTime endTime, int minutesPerDay) {

        return null;
    }
}
