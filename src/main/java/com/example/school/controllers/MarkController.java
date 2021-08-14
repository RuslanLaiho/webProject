package com.example.school.controllers;

import com.example.school.dto.Mark;
import com.example.school.dto.PersonAndMark;
import com.example.school.dto.SubjectAndMark;
import com.example.school.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarkController {

    @Autowired
    public MarkService markService;

    @PostMapping("/insertMark")
    public void insertMark(@RequestBody Mark mark) {
        markService.insertMark(mark);
    }

    @GetMapping("/SelectAllMarkOfStudent")
    public List<SubjectAndMark> selectAllMarkOfStudent(@RequestParam String name) {
        return markService.selectAllMarkOfStudent(name);
    }

    @GetMapping("/selectMarkOfSubject")
    public  List<PersonAndMark> selectMarkOfSubject(@RequestParam String subject) {
        return markService.selectMarkOfSubject(subject);
    }

}
