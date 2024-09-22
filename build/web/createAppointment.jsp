<%-- 
    Document   : createAppointment
    Created on : Jul 2, 2024, 8:21:33 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Appointment Page</title>
    </head>
    <body>
        <%
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
            }
        %>
        <form action="AppointmentServlet?action=create" method="post">
            Date: <input type="date" name="appointmentDate" required><br>
            Time: <input type="time" name="appointmentTime" required><br>
            Purpose: <input type="text" name="purpose" required><br>
            <input type="submit" value="Create Appointment">
        </form>
        <a href="dashboard.jsp">Back to Dashboard</a>

    </body>
</html>
