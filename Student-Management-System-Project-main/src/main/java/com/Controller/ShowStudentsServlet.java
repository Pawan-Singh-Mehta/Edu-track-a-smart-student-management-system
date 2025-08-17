package com.Controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.Class.student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowStudentsServlet")
public class ShowStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<student> studentList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
//            // Load MySQL JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish connection to the database
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db", "root", "root123");
//            System.out.println("Connected to Database!");
//
//            // SQL query to fetch all students
//            String sql = "SELECT * FROM students";
//            pstmt = con.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            // Populate the list with student data
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String studentName = rs.getString("student_name");
//                String rollNo = rs.getString("roll_no");
//                String course = rs.getString("course");
//                int marks = rs.getInt("marks");
//
//                student s = new student(id, studentName, rollNo, course, marks);
//                studentList.add(s);
//            }

            // Check if student list is empty
            if (studentList.isEmpty()) {
                System.out.println("No students found in the database.");
            } else {
                System.out.println("Fetched " + studentList.size() + " students from the database.");
            }

            // Set the student list in request attributes
            request.setAttribute("studentList", studentList);

            // Forward to JSP page to display the list
            RequestDispatcher dispatcher = request.getRequestDispatcher("showStudents.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching student list.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
