package org.lab.demo2.daos;

import org.lab.demo2.beans.Employee;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "root");
    }

    public ArrayList<Employee> listarEmpleados() {

        ArrayList<Employee> listaEmpleados = new ArrayList<Employee>();

        String sql = "select * from employees";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = fetchEmployeeData(rs);
                listaEmpleados.add(employee);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaEmpleados;
    }

    public Employee obtenerEmpleado(int employeeId) {

        Employee employee = null;

        String sql = "select * from employees e\n" +
                "where e.employee_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, employeeId);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    employee = fetchEmployeeData(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return employee;
    }

    public void guardarEmpleado(Employee employee) {

        String sql = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, salary, commission_pct, employee_id, job_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection() ;  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            /*setEmployeeData(employee, pstmt);*/
            String[] name = employee.getFullNameEmployee().split(" ");
            pstmt.setString(1, name[0]);
            pstmt.setString(2, name[1]);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone());
            pstmt.setString(5, employee.getBirthDate());
            if (employee.getSalary() == null) {
                pstmt.setNull(6, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(6, employee.getSalary());
            }
            if (employee.getCommission() == null) {
                pstmt.setNull(7, Types.DECIMAL);
            } else {
                pstmt.setBigDecimal(7, employee.getCommission());
            }
            pstmt.setInt(8, employee.getEmployeeId());
            pstmt.setString(9, employee.getJob());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actualizarEmpleado(Employee employee) {

        String sql = "UPDATE employees "
                + "SET first_name = ?, "
                + "last_name = ?, "
                + "email = ?, "
                + "phone_number = ?, "
                + "salary = ?, "
                + "commission_pct = ?, "
                + "hire_date = ? "
                + "WHERE employee_id = ?";


        /*String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, salary = ?, commission_pct = ?, hire_date = ? WHERE employee_id = ?";*/
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setEmployeeData(employee, pstmt);
            /*String[] nameParts = employee.getFullNameEmployee().split(" ");
            pstmt.setString(1, nameParts[0]);
            pstmt.setString(2, nameParts[1]);
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone());
            pstmt.setBigDecimal(5, employee.getSalary());
            if (employee.getCommission() == null) {
                pstmt.setNull(6, Types.DECIMAL);
            } else{
                pstmt.setBigDecimal(6, employee.getCommission());
            }
            pstmt.setString(7, employee.getBirthDate());*/
            pstmt.setInt(8, employee.getEmployeeId());



            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }




    public void borrarEmpleado(int employeeId) {

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM employees WHERE employee_id = ?")) {

            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private Employee fetchEmployeeData(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(1));
        String full = rs.getString(2) + " " + rs.getString(3);
        employee.setFullNameEmployee(full);
        employee.setEmail(rs.getString(4));
        employee.setPhone(rs.getString(6));
        employee.setBirthDate(rs.getString(7));
        employee.setSalary(rs.getBigDecimal(9));
        employee.setCommission(rs.getBigDecimal(10));

        return employee;
    }

    private void setEmployeeData (Employee employee, PreparedStatement pstmt) throws SQLException {

        String[] nameParts = employee.getFullNameEmployee().split(" ");
        pstmt.setString(1, nameParts[0]);
        pstmt.setString(2, nameParts[1]);
        pstmt.setString(3, employee.getEmail());
        pstmt.setString(4, employee.getPhone());
        pstmt.setString(7, employee.getBirthDate());
        pstmt.setBigDecimal(5, employee.getSalary());
        if (employee.getCommission() == null) {
            pstmt.setNull(6, Types.DECIMAL);
        } else {
            pstmt.setBigDecimal(6, employee.getCommission());
        }
    }





}