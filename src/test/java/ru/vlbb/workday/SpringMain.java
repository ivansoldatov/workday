package ru.vlbb.workday;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vlbb.workday.model.Role;
import ru.vlbb.workday.model.User;
import ru.vlbb.workday.service.UserService;
import ru.vlbb.workday.to.OperationTo;
import ru.vlbb.workday.web.employee.AdminRestController;
import ru.vlbb.workday.web.operation.OperationRestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserService userService = appCtx.getBean(UserService.class);

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "EmployeeName", "login", "password", Role.ADMIN));

            System.out.println();

            OperationRestController operationController = appCtx.getBean(OperationRestController.class);
            List<OperationTo> filteredMealsWithExcess =
                    operationController.getBetween(
                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(operationController.getBetween(null, null, null, null));
        }
    }
}
