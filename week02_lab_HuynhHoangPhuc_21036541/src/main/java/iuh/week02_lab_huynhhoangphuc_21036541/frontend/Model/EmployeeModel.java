package iuh.week02_lab_huynhhoangphuc_21036541.frontend.Model;

import iuh.week02_lab_huynhhoangphuc_21036541.backend.enums.EmloyeeStatus;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Employee;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.services.EmployeeService;
import jakarta.ejb.Local;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeModel {

    private final EmployeeService employeeService = new EmployeeService();

    public void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String name = request.getParameter("fullName");
        String s_dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(s_dob, formatter);

        Employee employee = new Employee(name, dob, email, phone, address, EmloyeeStatus.valueOf(status));

        EmployeeService service = new EmployeeService();
        service.insertEmployee(employee);
        response.sendRedirect("Employees.jsp");

    }

    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        employeeService.updateStatusE(id, EmloyeeStatus.QUIT);
        response.sendRedirect("Employees.jsp");
    }

    public void activeEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        employeeService.updateStatusE(id, EmloyeeStatus.ACTIVE);
        response.sendRedirect("Employees.jsp");
    }
}
