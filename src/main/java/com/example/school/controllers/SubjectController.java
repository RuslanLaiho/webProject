package com.example.school.controllers;

import com.example.school.dto.Subject;
import com.example.school.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    public SubjectService subjectService;

    @GetMapping("/selectAllSubject")
    public List<Subject> selectAllSubject() {
        return subjectService.selectAllSubject();
    }

    @PostMapping("/insertSubject")
    public void insertSubject(@RequestParam String subject) {subjectService.insertSubject(subject);}

}
