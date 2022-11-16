package ru.vlbb.workday;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.vlbb.workday.model.Employee;
import ru.vlbb.workday.model.Role;
import ru.vlbb.workday.service.EmployeeService;
import ru.vlbb.workday.web.employee.AdminRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            EmployeeService employeeService = appCtx.getBean(EmployeeService.class);

            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new Employee(null, "EmployeeName", "login", "password", Role.ADMIN));
        }
    }
}
