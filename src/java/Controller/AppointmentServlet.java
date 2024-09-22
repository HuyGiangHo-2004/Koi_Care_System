/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.DBUtils;

/**
 *
 * @author Khanh
 */
@WebServlet(name = "AppointmentServlet", urlPatterns = {"/AppointmentServlet"})
public class AppointmentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AppointmentServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AppointmentServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getParameter("action");

        if ("reminder".equals(action)) {
            sendReminders(request, response);
        }

    }
    
    private void sendReminders(HttpServletRequest request, HttpServletResponse response) {
        // Implement the reminder functionality (e.g., using JavaMail API for emails)
    }

    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                createAppointment(request, response);
                break;
            case "edit":
                editAppointment(request, response);
                break;
            case "cancel":
                cancelAppointment(request, response);
                break;
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void createAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String purpose = request.getParameter("purpose");

        try {
            Connection conn = DBUtils.initializeDatabase();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Appointments (userId, appointmentDate, appointmentTime, purpose, status) VALUES (?, ?, ?, ?, ?)");
            stmt.setInt(1, userId);
            stmt.setString(2, appointmentDate);
            stmt.setString(3, appointmentTime);
            stmt.setString(4, purpose);
            stmt.setString(5, "Scheduled");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            response.sendRedirect("dashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void editAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String appointmentDate = request.getParameter("appointmentDate");
        String appointmentTime = request.getParameter("appointmentTime");
        String purpose = request.getParameter("purpose");

        try {
            Connection conn = DBUtils.initializeDatabase();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Appointments SET appointmentDate = ?, appointmentTime = ?, purpose = ? WHERE id = ?");
            stmt.setString(1, appointmentDate);
            stmt.setString(2, appointmentTime);
            stmt.setString(3, purpose);
            stmt.setInt(4, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            response.sendRedirect("viewAppointments.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cancelAppointment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Connection conn = DBUtils.initializeDatabase();
            PreparedStatement stmt = conn.prepareStatement("UPDATE Appointments SET status = 'Cancelled' WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            response.sendRedirect("viewAppointments.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
