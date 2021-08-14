package com.example.school.dto;

public class StudentAndClass {
    public String studentName;
    public int classYear;
    public String className;

    @Override
    public String toString() {
        return studentName + ", " + classYear + ", " + className;
    }
}
