package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.model.OperationTo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.vlbb.workday.util.TimeUtil.isBetweenHalfOpen;

public class OperationUtil {

    public static final List<Operation> operations = Arrays.asList(
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 8, 5), LocalDateTime.of(2022, Month.NOVEMBER, 6, 8, 25), "Подготовка рабочего места"),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 9, 5), LocalDateTime.of(2022, Month.NOVEMBER, 6, 9, 45), "Подготовка договора для клиента"),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 10, 0), LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 0), "Работа с клиентом"),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 10), LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 45), "Оформление дела клиента и передача в архив")
    );

    public static void main(String[] args) {

//        List<ActionTo> list = filteredByStreams(actions, LocalTime.of(8, 0), LocalTime.of(17, 0), 7.0); */
    }

    public static List<OperationTo> getTos(List<Operation> operations, Double normHoursPerDay) {
        return operations.stream().map(operation -> createTo(operation, true)).collect(Collectors.toList());
    }

    public static List<OperationTo> filteredByStreams(List<Operation> operations, LocalTime startTime, LocalTime endTime, Double normHoursPerDay) {
        Map<LocalDate, Double> workTimePerDay = operations.stream()
                .collect(
                        Collectors.groupingBy(Operation::getStartDate, Collectors.summingDouble(Operation::getIntervalInHours))
                );

        return operations.stream()
                .filter(operation -> isBetweenHalfOpen(operation, startTime, endTime))
                .map(operation -> createTo(operation, workTimePerDay.get(operation.getStartDate()) > normHoursPerDay))
                .collect(Collectors.toList());
    }

    private static OperationTo createTo(Operation operation, boolean excess) {
        return new OperationTo(operation.getStartDateTime(), operation.getEndDateTime(), operation.getDescription(), excess);
    }
}
