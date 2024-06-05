package org.lab.demo2.servers;

import org.lab.demo2.beans.Employee;
import org.lab.demo2.beans.Job;
import org.lab.demo2.daos.EmployeeDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        EmployeeDAO employeeDAO = new EmployeeDAO();

        switch (action) {
            case "lista":
                request.setAttribute("listaEmpleados", employeeDAO.listarEmpleados());
                view = request.getRequestDispatcher("employees/listaEmployee.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                view = request.getRequestDispatcher("employees/agregarEmployee.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");

                    }

                    Employee emp = employeeDAO.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        request.setAttribute("empleado", emp);
                        view = request.getRequestDispatcher("employees/editarEmployee.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }

                break;
            /*case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Employee emp = employeeDAO.obtenerEmpleado(employeeId);

                    if (emp != null) {
                        employeeDAO.borrarEmpleado(employeeId);
                    }
                }

                response.sendRedirect("EmployeeServlet");
                break;*/

            case "borrar":
                String idd = request.getParameter("id");
                if(employeeDAO.obtenerEmpleado(Integer.parseInt(idd)) != null){
                    employeeDAO.borrarEmpleado(Integer.parseInt(idd));
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        EmployeeDAO employeeDAO = new EmployeeDAO();

        Employee employee = new Employee();
        String fullname = request.getParameter("first_name") + " " + request.getParameter("last_name");
        employee.setFullNameEmployee(fullname);
        employee.setEmail(request.getParameter("email"));
        employee.setPhone(request.getParameter("phone"));
        employee.setBirthDate(request.getParameter("hire_date"));
        employee.setSalary(request.getParameter("salary").equals("") ? null :new BigDecimal(request.getParameter("salary")));
        employee.setCommission(request.getParameter("commission").equals("") ? null : new BigDecimal(request.getParameter("commission")));
        employee.setJob(request.getParameter("job_id"));
        employee.setEmployeeId(Integer.parseInt(request.getParameter("employee_id")));

        switch (action) {
            case "guardar":
                employeeDAO.guardarEmpleado(employee);

                response.sendRedirect("EmployeeServlet");
                break;


            case "actualizar":
                employee.setEmployeeId(Integer.parseInt(request.getParameter("employee_id"))); //no olvidar que para actualizar se debe enviar el ID

                employeeDAO.actualizarEmpleado(employee);

                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");

                break;
        }
    }

}