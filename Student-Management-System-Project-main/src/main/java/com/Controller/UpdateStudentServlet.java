package com.Controller;

import java.io.IOException;
import java.sql.SQLException;

import com.Class.student;
import com.Dao.StudentDao1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String idStr = request.getParameter("id");
        String studentName = request.getParameter("studentName"); // Corrected parameter name
        String rollNo = request.getParameter("rollNo"); // Use rollNo as a String
        String course = request.getParameter("course");
        String marksStr = request.getParameter("marks");

        // Initializing DAO and result variable
        StudentDao1 studentDao = new StudentDao1();
        String result = "failure";
        RequestDispatcher successDispatcher = request.getRequestDispatcher("home1.html");
        RequestDispatcher errorDispatcher = request.getRequestDispatcher("ErrorPage.jsp");

        try {
            // Parse inputs
            int id = Integer.parseInt(idStr);
            int marks = Integer.parseInt(marksStr);

            // Create student object and set properties
            student student = new student();
            student.setId(id);
            student.setStudentName(studentName);
            student.setRollNo(rollNo); // Use rollNo as String
            student.setCourse(course);
            student.setMarks(marks);

            // Perform the update operation via DAO
            result = studentDao.updateStudent(student);

            if ("success".equals(result)) {
                // Forward to home page upon successful update
                successDispatcher.forward(request, response);
            } else {
                // Show error page if update fails
                request.setAttribute("errorMessage", "Error updating student: " + result);
                errorDispatcher.include(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the SQL error
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            errorDispatcher.include(request, response);
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Log input conversion error
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            errorDispatcher.include(request, response);
        } catch (Exception e) {
            e.printStackTrace(); // Log unexpected errors
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            errorDispatcher.include(request, response);
        }
    }
}
