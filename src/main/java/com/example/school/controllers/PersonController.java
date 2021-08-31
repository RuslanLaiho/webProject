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
    public void insertPerson(@RequestBody String name, @RequestBody String occupation) {
        personService.insertPerson(name, occupation);
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
    public Person selectPerson(@RequestParam String name){
        return personService.selectPerson(name);
    }
}
