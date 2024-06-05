<%@page import="java.util.ArrayList" %>
<%@page import="org.lab.demo2.beans.Employee" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Nuevo empleado</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="EmployeeServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo usuario</h1>
            <hr>
            <div class="mb-3">
                <label for="employee_id">Id del empleado</label>
                <input type="text" class="form-control form-control-sm" name="employee_id" id="employee_id">
            </div>
            <div class="mb-3">
                <label for="first_name">First Name</label>
                <input type="text" class="form-control form-control-sm" name="first_name" id="first_name">
            </div>
            <div class="mb-3">
                <label for="last_name">Last Name</label>
                <input type="text" class="form-control form-control-sm" name="last_name" id="last_name">
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                <input type="text" class="form-control form-control-sm" name="email" id="email">
            </div>
            <div class="mb-3">
                <label for="phone">Phone number</label>
                <input type="text" class="form-control form-control-sm" name="phone" id="phone">
            </div>
            <div class="mb-3">
                <label for="job_id">Ingrese el job_id</label>
                <input type="text" class="form-control form-control-sm" name="job_id" id="job_id">
            </div>
            <div class="mb-3">
                <label for="hire_date">Hire date</label>
                <input type="text" class="form-control form-control-sm" name="hire_date" id="hire_date">
            </div>
            <div class="mb-3">
                <label for="salary">Salary</label>
                <input type="text" class="form-control form-control-sm" name="salary" id="salary">
            </div>
            <div class="mb-3">
                <label for="commission">Commision PCT</label>
                <input type="text" class="form-control form-control-sm" name="commission" id="commission">
            </div>
            <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>

