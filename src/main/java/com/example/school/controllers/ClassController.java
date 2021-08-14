package com.example.school.controllers;

import com.example.school.dto.Class;
import com.example.school.dto.Person;
import com.example.school.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    public ClassService classService;

    @GetMapping("/selectAllClass")
    public List<Class> selectAllClass() {
        return classService.selectAllClass();
    }

    @GetMapping("/selectListOfClass")
    public List<Person> selectListOfClass(int year, String className) {
        return classService.selectListOfClass(year, className);
    }

    @GetMapping("/selectClass")
    public Class selectClass(int year, String className) {
        return classService.selectClass(year, className);
    }

    @PostMapping("/insertClass")
    public void insertClass(@RequestParam("className") String className, @RequestParam("classYear") int classYear, @RequestParam("classTeacher") String classTeacher) {
        classService.insertClass(className, classYear, classTeacher);
    }

    @PostMapping("/updateClassYear")
    public void UpdateClassYear(@RequestParam("updateYear") int updateYear, @RequestParam("classYear") int classYear, @RequestParam("className") String className) {
        classService.updateClassYear(updateYear, classYear, className);
    }

}
