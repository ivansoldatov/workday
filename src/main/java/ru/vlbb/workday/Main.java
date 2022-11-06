package ru.vlbb.workday;

import java.time.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.format("Hello in workday!");
        LocalDate date = LocalDate.now();

        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 37);
//        long stTime = startTime.toEpochSecond(date, ZoneOffset.UTC);
//        long enTime = endTime.toEpochSecond(date, ZoneOffset.UTC);
//        double interval = (enTime-stTime)/60/60.0;
//        System.out.println(interval);
//        System.out.println(Math.round(interval*100.0)/100.0);




        Instant start = Instant.now();
// выполнение какой-то логики
        Thread.sleep(1000);
        Instant finish = Instant.now();
        long elapsed = Duration.between(start, finish).toMillis();
        System.out.println("Прошло времени, мс: " + elapsed);
    }
}
