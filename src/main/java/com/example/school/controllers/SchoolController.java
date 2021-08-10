package com.example.school.controllers;

import com.example.school.dto.Mark;
import com.example.school.dto.Person;
import com.example.school.dto.Class;
import com.example.school.dto.SubjectAndPerson;
import com.example.school.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SchoolController {

    @Autowired
    public SchoolService schoolService;

    @GetMapping("/test")
    public String test() {
        return "ZAWARDOOOO";
    }

    @GetMapping("/selectAllPerson")
    public List<Person> selectAllPerson() {
        return schoolService.selectAllPerson();
    }

    @GetMapping("/selectAllClass")
    public List<Class> selectAllClass() {
        return schoolService.selectAllClass();
    }

    @GetMapping("/selectListOfClass")
    public List<Person> selectListOfClass(int year, String className) {
        return schoolService.selectClassInfo(year, className);
    }

    @GetMapping("/selectPersonByMark")
    public List<SubjectAndPerson> selectPersonByMark(int mark) {
        return schoolService.selectPersonByMark(mark);
    }

    @PostMapping("/insertMark")
    public void insertMark(@RequestBody Mark mark) {
        schoolService.insertMark(mark);
    }
}
