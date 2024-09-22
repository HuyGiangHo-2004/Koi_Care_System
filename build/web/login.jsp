<%-- 
    Document   : login
    Created on : Jul 2, 2024, 7:21:49 PM
    Author     : Khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="UserServlet?action=login" method="post">
            Username: <input type="text" name="username" required><br>
            Password: <input type="password" name="password" required><br>
            <input type="submit" value="Login">
        </form>
        <%
            String error = request.getParameter("error");
            if ("invalid".equals(error)) {
        %>
        <p style="color:red">Invalid username or password</p>
        <%
            }
        %>

    </body>
</html>
