package com.example.school.controllers;

import com.example.school.dto.Subject;
import com.example.school.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    public SubjectService subjectService;

    @GetMapping("/getAll")
    public List<Subject> getAll() {
        return subjectService.getAll();
    }

    @PostMapping("/save")
    public void save(@RequestBody String subject) {subjectService.save(subject);}

}
