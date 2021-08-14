package com.example.school.dto;

public class SubjectAndMark {
    public String subject;
    public int mark;

    @Override
    public String toString() {
        return subject + ", " + mark;
    }
}
