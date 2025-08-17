package com.Class;

public class student {
    private int id;
    private String studentName;
    private String rollNo;  // Changed to String if rollNo is alphanumeric
    private String course;
    private int marks;  // Changed to int if marks are numeric
    // Getters and Setters

    public student() {
        super();
    }

    public student(int id, String studentName, String rollNo, String course, int marks) {
        super();
        this.id = id;
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "student [id=" + id + ", studentName=" + studentName + ", rollNo=" + rollNo + ", course=" + course
                + ", marks=" + marks + "]";
    }
}
