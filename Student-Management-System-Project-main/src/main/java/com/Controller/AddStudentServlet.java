package com.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import com.Class.student;
import com.Dao.StudentDao;

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Show the form page
        response.sendRedirect("addstudent.html");  // Redirect to HTML form
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String studentName = request.getParameter("name");
            String rollNoStr = request.getParameter("rollNo");
            String course = request.getParameter("course");
            String marksStr = request.getParameter("marks");

            if (studentName == null || studentName.isEmpty() ||
                rollNoStr == null || rollNoStr.isEmpty() ||
                course == null || course.isEmpty() ||
                marksStr == null || marksStr.isEmpty()) {
                throw new IllegalArgumentException("All fields are required.");
            }

            int marks = Integer.parseInt(marksStr);

            student s = new student();
            s.setStudentName(studentName);
            s.setRollNo(rollNoStr);
            s.setCourse(course);
            s.setMarks(marks);

            StudentDao dao = new StudentDao();
            String result = dao.addStudent(s);

            if ("success".equals(result)) {
                out.println("<script>");
                out.println("alert('Student added successfully.');");
                out.println("window.location='home1.html';"); // redirect to home
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('Error adding student: " + result + "');");
                out.println("window.history.back();");
                out.println("</script>");
            }

        } catch (IllegalArgumentException e) {
            out.println("<script>");
            out.println("alert('Validation Error: " + e.getMessage() + "');");
            out.println("window.history.back();");
            out.println("</script>");
        } catch (SQLException e) {
            out.println("<script>");
            out.println("alert('Database error: " + e.getMessage() + "');");
            out.println("window.history.back();");
            out.println("</script>");
        } catch (Exception e) {
            out.println("<script>");
            out.println("alert('Unexpected error: " + e.getMessage() + "');");
            out.println("window.history.back();");
            out.println("</script>");
        }
    }
}
