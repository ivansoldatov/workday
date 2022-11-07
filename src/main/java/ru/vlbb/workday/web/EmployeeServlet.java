package ru.vlbb.workday.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class EmployeeServlet extends HttpServlet {
    private static final Logger log = getLogger(EmployeeServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to employees");

//        request.getRequestDispatcher("/employees.jsp").forward(request, response);
        response.sendRedirect("employees.jsp");
    }
}