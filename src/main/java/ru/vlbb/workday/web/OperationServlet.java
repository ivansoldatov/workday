package ru.vlbb.workday.web;

import org.slf4j.Logger;
import ru.vlbb.workday.util.OperationUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.slf4j.LoggerFactory.getLogger;

public class OperationServlet extends HttpServlet {
    private static final Logger log = getLogger(OperationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("operations", OperationUtil.getTos(OperationUtil.operations,33.3));
                request.getRequestDispatcher("/operations.jsp").forward(request, response);
                break;
        }
    }
}
