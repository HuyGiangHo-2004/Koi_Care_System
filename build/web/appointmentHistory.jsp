<%-- 
    Document   : appointmentHistory
    Created on : Jul 2, 2024, 8:37:45 PM
    Author     : Khanh
--%>

<%@page import="util.DBUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment History Page</title>
    </head>
    <body>
        <%
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
            }

            int userId = (int) session.getAttribute("userId");
            Connection conn = DBUtils.initializeDatabase();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Appointments WHERE userId = ? AND status = 'Completed'");
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
        %>
        <h2>Appointment History</h2>
        <table border="1">
            <tr>
                <th>Date</th>
                <th>Time</th>
                <th>Purpose</th>
                <th>Status</th>
            </tr>
        <%
            while (rs.next()) {
        %>
            <tr>
                <td><%= rs.getDate("appointmentDate") %></td>
                <td><%= rs.getTime("appointmentTime") %></td>
                <td><%= rs.getString("purpose") %></td>
                <td><%= rs.getString("status") %></td>
            </tr>
        <%
            }
            stmt.close();
            conn.close();
        %>
        </table>
    <a href="dashboard.jsp">Back to Dashboard</a>

    </body>
</html>
