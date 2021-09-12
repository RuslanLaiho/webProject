package com.example.school.controllers;

import com.example.school.dto.Person;
import com.example.school.dto.StudentAndClass;
import com.example.school.dto.SubjectAndPerson;
import com.example.school.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    public PersonService personService;

    @GetMapping("/selectAllPerson")
    public List<Person> selectAllPerson() {
        return personService.selectAllPerson();
    }

    @PostMapping("/insertPerson")
    public void insertPerson(@RequestBody Person person) {
        personService.insertPerson(person.name, person.occupation, person.birthYear, person.phone);
    }

    @GetMapping("/selectPersonByMark")
    public List<SubjectAndPerson> selectPersonByMark(int mark) {
        return personService.selectPersonByMark(mark);
    }

    @GetMapping("/selectAllStudent")
    public List<StudentAndClass> selectAllStudent() {
        return personService.selectAllStudent();
    }

    @GetMapping("/selectPerson")
    public Person selectPerson(@RequestParam String name, @RequestParam int birthYear, @RequestParam String phone){
        return personService.selectPerson(name, birthYear, phone);
    }

    @GetMapping("/selectAllTeacher")
    public List<Person> selectAllTeacher() {return personService.selectAllTheacher();}
}
