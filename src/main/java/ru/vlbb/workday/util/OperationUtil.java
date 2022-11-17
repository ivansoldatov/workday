package ru.vlbb.workday.util;

import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.to.OperationTo;

import java.time.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OperationUtil {

    public static final int DEFAULT_MINUTES_IN_DAY = 200;

    public static final List<Operation> operations = Arrays.asList(
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 8, 5), LocalDateTime.of(2022, Month.NOVEMBER, 6, 8, 25), "Подготовка рабочего места", 1),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 9, 5), LocalDateTime.of(2022, Month.NOVEMBER, 6, 9, 45), "Подготовка договора для клиента", 1),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 10, 0), LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 0), "Работа с клиентом", 1),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 10), LocalDateTime.of(2022, Month.NOVEMBER, 6, 11, 45), "Оформление дела клиента и передача в архив", 1),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 9, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 11, 5), "Работа с клиентом", 1),
            new Operation(LocalDateTime.of(2022, Month.NOVEMBER, 7, 12, 0), LocalDateTime.of(2022, Month.NOVEMBER, 7, 17, 0), "Подготовка отчета для проверки ЦБ", 1)
    );

    public static void main(String[] args) {

//        List<ActionTo> list = filteredByStreams(actions, LocalTime.of(8, 0), LocalTime.of(17, 0), 7.0); */
    }

    public static List<OperationTo> getTos(Collection<Operation> operations, int normInDay) {
        return filteredByPredicate(operations, normInDay, operation -> true);
    }

    public static List<OperationTo> getFilteredTos(Collection<Operation> operations, int normInDay, LocalTime startTime, LocalTime endTime) {
        return filteredByPredicate(operations, normInDay, operation -> DateTimeUtil.isBetweenHalfOpen(operation, startTime, endTime));
    }

    public static List<OperationTo> filteredByPredicate(Collection<Operation> operations, int normInDay, Predicate<Operation> filter) {
        Map<LocalDate, Integer> workTimePerDay = operations.stream()
                .collect(
                        Collectors.groupingBy(Operation::getStartDate, Collectors.summingInt(Operation::getIntervalInMinutes))
                );

        return operations.stream()
                .filter(filter)
                .map(operation -> createTo(operation, workTimePerDay.get(operation.getStartDate()) > normInDay))
                .collect(Collectors.toList());
    }

    private static OperationTo createTo(Operation operation, boolean excess) {
        return new OperationTo(operation.getId(), operation.getStartDateTime(), operation.getEndDateTime(), operation.getDescription(), excess, operation.getEmployeeId());
    }
}
