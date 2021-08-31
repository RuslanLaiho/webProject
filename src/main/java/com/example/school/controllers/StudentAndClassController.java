package com.example.school.controllers;

import com.example.school.services.StudentAndClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentAndClassController {

    @Autowired
    public StudentAndClassService studentAndClassService;

    @PostMapping("/insertStudentAndClass")
    public void insertStudentAndClass(@RequestBody String studentName, @RequestBody int classYear, @RequestBody String className) {
        studentAndClassService.insertStudentAndClass(studentName, classYear, className);
    }

}
