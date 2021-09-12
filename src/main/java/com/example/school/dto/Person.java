package com.example.school.dto;

public class Person {
    public int personId;
    public String name;
    public String occupation;
    public int birthYear;
    public String phone;

    public String toString(){
        return personId + name + occupation + birthYear + personId;
    }


}
