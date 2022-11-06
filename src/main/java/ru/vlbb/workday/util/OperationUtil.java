package ru.vlbb.workday.util;

import ru.vlbb.workday.model.OperationTo;
import ru.vlbb.workday.model.Operation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.vlbb.workday.util.TimeUtil.isBetweenHalfOpen;

public class OperationUtil {

    public static void main(String[] args) {
   /*     List<Action> actions = Arrays.asList(
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(8, 15), LocalTime.of(8, 30), "Подготовка рабочего места"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(9, 0), LocalTime.of(10, 0), "Подготовка договора для клиента"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(10, 15), LocalTime.of(11, 45), "Работа с клиентом"),
                new Action(LocalDate.parse("15.10.2022", dtf), LocalTime.of(12, 0), LocalTime.of(13, 0), "Перерыв"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(8, 15), LocalTime.of(8, 30), "Подготовка рабочего места"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(9, 0), LocalTime.of(10, 0), "Подготовка договора для клиента"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(10, 15), LocalTime.of(11, 45), "Работа с клиентом"),
                new Action(LocalDate.parse("16.10.2022", dtf), LocalTime.of(13, 0), LocalTime.of(17, 0), "Перерыв")
        );
        List<ActionTo> list = filteredByStreams(actions, LocalTime.of(8, 0), LocalTime.of(17, 0), 7.0); */
    }

    public static List<Operation> filteredByStreams(List<OperationTo> actions, LocalTime startTime, LocalTime endTime, Double normHoursPerDay) {
        Map<LocalDate, Double> workTimePerDay = actions.stream()
                .collect(
                        Collectors.groupingBy(OperationTo::getStartDate, Collectors.summingDouble(OperationTo::getIntervalInHours))
                );

        return actions.stream()
                .filter(action -> isBetweenHalfOpen(action, startTime, endTime))
                .map(action -> createTo(action, workTimePerDay.get(action.getStartDate()) > normHoursPerDay))
                .collect(Collectors.toList());
    }

    private static Operation createTo(OperationTo action, boolean excess) {
        return new Operation(action.getStartDateTime(), action.getEndDateTime(), action.getDescription(), excess);
    }
}
