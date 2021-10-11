package com.example.school.controllers;

import com.example.school.services.StudentAndClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/StudentAndClass")
public class StudentAndClassController {

    @Autowired
    public StudentAndClassService studentAndClassService;

    @PostMapping("/save")
    public void save(@RequestBody String studentName, @RequestBody int classYear, @RequestBody String className) {
        studentAndClassService.save(studentName, classYear, className);
    }

}
