package ru.vlbb.workday.web;

import org.slf4j.Logger;
import ru.vlbb.workday.model.Operation;
import ru.vlbb.workday.repository.inmemory.InMemoryOperationRepository;
import ru.vlbb.workday.util.OperationUtil;

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

    private InMemoryOperationRepository repository;

    @Override
    public void init() {
        repository = new InMemoryOperationRepository();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("delete id={}", id);
                repository.delete(id);
                response.sendRedirect("operations");
                break;
            case "create":
            case "update":
                final Operation operation = "create".equals(action) ?
                        new Operation(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), LocalDateTime.now().plusMinutes(15).truncatedTo(ChronoUnit.MINUTES), "new operation") :
                        repository.get(getId(request));
                request.setAttribute("operation", operation);
                request.getRequestDispatcher("/operationForm.jsp").forward(request, response);
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("operations", OperationUtil.getTos(repository.getAll(), 33.3));
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

        log.info(operation.isNew() ? "Create {}" : "Update {}", operation);
        repository.save(operation);
        response.sendRedirect("operations");
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
