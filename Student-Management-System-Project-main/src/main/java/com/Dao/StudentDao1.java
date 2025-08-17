package com.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Class.student;
import com.Utillity.DBConnection;

public class StudentDao1 {
    private Connection con;

    // Constructor initializes the database connection
    public StudentDao1() {
        con = DBConnection.getDataConnection();
    }

    // Method to delete a student by ID
    public String deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0) ? "success" : "failure"; // Student not found
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Method to search for a student by ID
    public List<student> searchStudentById(int studentId) throws SQLException {
        List<student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                student s = new student();
                s.setId(rs.getInt("id"));
                s.setStudentName(rs.getString("student_name"));
                s.setRollNo(rs.getString("roll_no"));  // Updated to use setRollNo with String
                s.setCourse(rs.getString("course"));
                s.setMarks(rs.getInt("marks"));  // Updated to use setMarks with int
                students.add(s);
            }
        }
        return students;
    }

    // Method to update student details
    public String updateStudent(student s) throws SQLException {
        String sql = "UPDATE students SET student_name = ?, roll_no = ?, course = ?, marks = ? WHERE id = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, s.getStudentName());
            pstmt.setString(2, s.getRollNo());  // Updated to use setRollNo with String
            pstmt.setString(3, s.getCourse());
            pstmt.setInt(4, s.getMarks());  // Updated to use setMarks with int
            pstmt.setInt(5, s.getId());

            int rowsAffected = pstmt.executeUpdate();
            return (rowsAffected > 0) ? "success" : "failure"; // Return success or failure
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Propagate the exception
        }
    }
 // Fetch all students from the database
    public List<student> getAllStudents() throws SQLException {
        List<student> students = new ArrayList<>();
        

        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM students")) {
        	ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                student s = new student();
                        s.setId(rs.getInt("id"));
                        s.setStudentName(rs.getString("student_name"));
                        s.setRollNo(rs.getString("roll_no"));
                        s.setCourse(rs.getString("course"));
                        s.setMarks(rs.getInt("marks"));
                System.out.println(s);
                students.add(s);
            }
        }
        
        System.out.println("Fetched " + students.size() + " students from the database.");
        return students;
    }
}