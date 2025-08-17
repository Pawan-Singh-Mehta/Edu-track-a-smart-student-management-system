<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Class.student" %>
<%@ page import="com.Dao.StudentDao1" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show All Students</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    <style>
        /* Global Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #f4f1f9, #e8e4f5);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            color: #333;
        }

        .container {
            background: #fff;
            padding: 40px;
            border-radius: 10px;
            width: 100%;
            max-width: 1000px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            font-size: 28px;
            color: #6a53a3;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            font-size: 16px;
        }

        thead {
            background: linear-gradient(135deg, #9a7cd8, #7f6fd7);
            color: white;
        }

        th, td {
            padding: 12px 15px;
            text-align: center;
            border: 1px solid #ddd;
        }

        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tbody tr:hover {
            background-color: #f1f1f1;
        }

        .no-data {
            text-align: center;
            font-size: 18px;
            color: #e74c3c;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>All Students</h1>
        <%
        List<student> students = new ArrayList<>();
        StudentDao1 std = new StudentDao1();
        students = std.getAllStudents();

        if (students == null || students.isEmpty()) {
        %>
            <div class="no-data">No students found.</div>
        <% 
        } else { 
        %>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Roll No</th>
                        <th>Course</th>
                        <th>Marks</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (student s : students) { %>
                        <tr>
                            <td><%= s.getId() %></td>
                            <td><%= s.getStudentName() %></td>
                            <td><%= s.getRollNo() %></td>
                            <td><%= s.getCourse() %></td>
                            <td><%= s.getMarks() %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% 
        }
        %>
    </div>
</body>
</html>
