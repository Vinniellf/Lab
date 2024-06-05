<%@page import="org.lab.demo2.beans.Employee" %>
<%@page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaEmpleados" type="java.util.ArrayList<org.lab.demo2.beans.Employee>" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../includes/bootstrap_header.jsp"/>
    <title>Lista empleados</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de empleados</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Empleados</li>
        </ol>
    </nav>
    <a href="<%= request.getContextPath()%>/EmployeeServlet?action=agregar" class="btn btn-primary mb-4">
        Agregar nuevo empleado</a>
    <table class="table">
        <thead>
        <tr>
            <th>id#</th>
            <th>Employee</th>
            <th>Email</th>
            <th>Salary</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Employee e : listaEmpleados) {
        %>
        <tr>
            <td><%= e.getEmployeeId()%>
            </td>
            <td><%= e.getFullNameEmployee()%>
            </td>
            <td><%= e.getEmail()%>
            </td>
            <td><%= e.getSalary()%>
            </td>
            <td>
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/EmployeeServlet?action=editar&id=<%=e.getEmployeeId()%>">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="<%=request.getContextPath()%>/EmployeeServlet?action=borrar&id=<%=e.getEmployeeId()%>">
                    <i class="bi bi-trash3"></i>
                </a>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
<jsp:include page="../includes/bootstrap_footer.jsp"/>
</body>
</html>