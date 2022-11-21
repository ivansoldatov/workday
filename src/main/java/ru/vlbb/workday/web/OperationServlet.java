package ru.vlbb.workday.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.util.OperationUtil;
import ru.vlbb.workday.web.operation.OperationRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class OperationServlet extends HttpServlet {
    private static final Logger log = getLogger(OperationServlet.class);

    private ConfigurableApplicationContext springContext;
    private OperationRestController operationController;

//    private InMemoryOperationRepository repository;

    @Override
    public void init() {
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        operationController = springContext.getBean(OperationRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("delete id={}", id);
                operationController.delete(id);
                response.sendRedirect("operations");
                break;
            case "create":
            case "update":
                final Operation operation = "create".equals(action) ?
                        new Operation(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().plusMinutes(15).truncatedTo(ChronoUnit.MINUTES), "new operation") :
                        operationController.get(getId(request));
                request.setAttribute("operation", operation);
                request.getRequestDispatcher("/operationForm.jsp").forward(request, response);
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("operations", OperationUtil.getTos(operationController.getAll(), OperationUtil.DEFAULT_MINUTES_IN_DAY));
                request.getRequestDispatcher("/operations.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        LocalDateTime startDateTime = LocalDateTime.of(LocalDate.parse(request.getParameter("startDate")), LocalTime.parse(request.getParameter("startTime")));
        LocalDateTime endDateTime = LocalDateTime.of(LocalDate.parse(request.getParameter("startDate")), LocalTime.parse(request.getParameter("endTime")));
        String description = request.getParameter("description");

        Operation operation = new Operation(id.isEmpty() ? null : Integer.valueOf(id), startDateTime, endDateTime, description);

        if (StringUtils.hasLength(request.getParameter("id"))) {
            operationController.update(operation, getId(request));
        } else {
            operationController.create(operation);
        }
        response.sendRedirect("operations");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
