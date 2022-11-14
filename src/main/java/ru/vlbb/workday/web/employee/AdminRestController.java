package ru.vlbb.workday.web.employee;



import org.springframework.stereotype.Controller;
import ru.vlbb.workday.model.Employee;

import java.util.List;

@Controller
public class AdminRestController extends AbstractEmployeeController {

    @Override
    public List<Employee> getAll() {
        return super.getAll();
    }

    @Override
    public Employee get(int id) {
        return super.get(id);
    }

    @Override
    public Employee create(Employee employee) {
        return super.create(employee);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Employee employee, int id) {
        super.update(employee, id);
    }

}