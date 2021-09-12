package com.example.school.controllers;

import com.example.school.dto.Class;
import com.example.school.dto.InsertClassDTO;
import com.example.school.dto.Person;
import com.example.school.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    public void insertClass(@RequestBody InsertClassDTO insertClassDTO) {
        classService.insertClass(insertClassDTO.className, insertClassDTO.classYear, insertClassDTO.classTeacher, insertClassDTO.birthYear, insertClassDTO.phone);
    }

    @PostMapping("/updateClassYear")
    public void UpdateClassYear(@RequestBody int updateYear, @RequestBody int classYear, @RequestBody String className) {
        classService.updateClassYear(updateYear, classYear, className);
    }

}
