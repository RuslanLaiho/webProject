package com.example.school.dto;

public class Person {
    public int personId;
    public String name;
    public String occupation;

    public String toString() {
        return personId + ", " + name + ", " + occupation;
    }

    public Person(String name, String occupation) {
        this.name = name;
        this.occupation = occupation;
    }

    public Person() {
    }

}
