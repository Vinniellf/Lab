<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.lab.demo2.beans.Employee" %>
<jsp:useBean id="empleado" type="org.lab.demo2.beans.Employee" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Editar empleado</title>
</head>
<body>
<div class='container mb-4'>
    <div class="row justify-content-center">
        <h1 class='mb-3'>Editar usuario</h1>
        <hr>
        <form method="POST" action="EmployeeServlet?action=actualizar" class="col-md-6 col-lg-6">
            <input type="hidden" name="employee_id" value="<%= empleado.getEmployeeId()%>"/>
            <div class="mb-3">
                <label for="first_name">FullName</label>
                <input type="text" class="form-control form-control-sm" name="first_name" id="first_name"
                       value="<%= empleado.getFullNameEmployee() == null ? "" : empleado.getFullNameEmployee()%>">
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                <input type="text" class="form-control form-control-sm" name="email" id="email"
                       value="<%= empleado.getEmail() == null ? "" : empleado.getEmail()%>">
            </div>
            <div class="mb-3">
                <label for="phone">Phone number</label>
                <input type="text" class="form-control form-control-sm" name="phone" id="phone"
                       value="<%= empleado.getPhone() == null ? "" : empleado.getPhone()%>">
            </div>
            <div class="mb-3">
                <label for="hire_date">Hire date</label>
                <input type="text" class="form-control form-control-sm" name="hire_date" id="hire_date"
                       value="<%= empleado.getBirthDate()== null ? "" : empleado.getBirthDate()%>">
            </div>
            <div class="mb-3">
                <label for="salary">Salary</label>
                <input type="text" class="form-control form-control-sm" name="salary" id="salary"
                       value="<%= empleado.getSalary() == null ? "" : empleado.getSalary()%>">
            </div>
            <div class="mb-3">
                <label for="commission">Commision PCT</label>
                <input type="text" class="form-control form-control-sm" name="commission" id="commission"
                       value="<%= empleado.getCommission() == null ? "" : empleado.getCommission()%>">
            </div>


            <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Actualizar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>