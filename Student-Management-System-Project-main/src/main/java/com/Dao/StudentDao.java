package com.Dao;
import java.sql.*;

import com.Utillity.*;
import com.Class.student;

public class StudentDao {

    public String addStudent(student sobj) throws SQLException {
        String sname = sobj.getStudentName();
        String cname = sobj.getCourse();
        String rno = sobj.getRollNo();  // rollNo is a String
        int mrk = sobj.getMarks();  // marks is an int

        Connection con = DBConnection.getDataConnection();

        // Updated SQL query to match the new table structure
        String sql = "INSERT INTO students (student_name, roll_no, course, marks, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, sname);  // Setting student_name
            stmt.setString(2, rno);    // Setting roll_no as String
            stmt.setString(3, cname);  // Setting course
            stmt.setInt(4, mrk);       // Setting marks as int

            int i = stmt.executeUpdate();
            if (i > 0) {
                return "success";
            } else {
                return "Record not Inserted";
            }
        }
    }
}
