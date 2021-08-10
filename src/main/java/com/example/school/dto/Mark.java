package com.example.school.dto;

public class Mark {

    public String subject;
    public int mark;
    public String studentName;
    public String teacherName;

    public String toString() {
        return subject + ", " + mark + ", " + studentName + ", " + teacherName;
    }


}
