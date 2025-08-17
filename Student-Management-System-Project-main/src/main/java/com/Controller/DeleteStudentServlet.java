package com.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.Dao.StudentDao1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        StudentDao1 studentDao = new StudentDao1();

        try {
            // Validate input
            if (idStr == null || idStr.trim().isEmpty()) {
                throw new NumberFormatException("Student ID cannot be empty.");
            }

            int id = Integer.parseInt(idStr.trim());
            String result = studentDao.deleteStudent(id);

            if ("success".equals(result)) {
                // Success message
                request.setAttribute("successMessage", "Student deleted successfully.");
                request.getRequestDispatcher("home1.html").forward(request, response);
            } else {
                // If the deletion failed (e.g., invalid ID)
                throw new Exception("No student found with the provided ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
