<%-- 
    Document   : editAppointment
    Created on : Jul 2, 2024, 8:32:31 PM
    Author     : Khanh
--%>

<%@page import="util.DBUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Appointment Page</title>
    </head>
    <body>
        <%
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
            }

            int id = Integer.parseInt(request.getParameter("id"));
            Connection conn = DBUtils.initializeDatabase();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointments WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
        %>
        <form action="AppointmentServlet?action=edit" method="post">
            <input type="hidden" name="id" value="<%= id %>">
            Date: <input type="date" name="appointmentDate" value="<%= rs.getDate("appointmentDate") %>" required><br>
            Time: <input type="time" name="appointmentTime" value="<%= rs.getTime("appointmentTime") %>" required><br>
            Purpose: <input type="text" name="purpose" value="<%= rs.getString("purpose") %>" required><br>
            <input type="submit" value="Save Changes">
        </form>
        <%
            }
            stmt.close();
            conn.close();
        %>
        <a href="viewAppointments.jsp">Back to Appointments</a>

    </body>
</html>
