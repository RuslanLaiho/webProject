package com.example.school.dto;

public class PersonAndMark {
    public  String personName;
    public int mark;

    @Override
    public String toString() {
        return  personName + ", " + mark;
    }
}
