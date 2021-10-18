package com.example.school.controllers;

import com.example.school.dto.Person;
import com.example.school.dto.StudentAndClass;
import com.example.school.dto.SubjectAndPerson;
import com.example.school.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    public PersonService personService;

    @GetMapping("/getAll")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/findByFilterClass")
    public List<Person> findByFilterClass(int year, String className) {
        return personService.findByFilterClass(year, className);
    }

    @PostMapping("/save")
    public void save(@RequestBody Person person) {
        personService.save(person.name, person.occupation, person.birthYear, person.phone);
    }

    @GetMapping("/findByFilterPerson")
    public List<SubjectAndPerson> findByFilterPerson(int mark) {
        return personService.findByFilterPerson(mark);
    }

    @GetMapping("/findStudent")
    public List<StudentAndClass> findStudent() {
        return personService.findStudent();
    }

    @GetMapping("/find")
    public Person find(@RequestParam String name, @RequestParam int birthYear, @RequestParam String phone){
        return personService.find(name, birthYear, phone);
    }

    @GetMapping("/findAllTeacher")
    public List<Person> findAllTeacher() {return personService.selectAllTheacher();}
}
