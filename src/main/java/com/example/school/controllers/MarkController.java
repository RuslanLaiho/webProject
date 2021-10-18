package com.example.school.controllers;

import com.example.school.dto.Mark;
import com.example.school.dto.PersonAndMark;
import com.example.school.dto.SubjectAndMark;
import com.example.school.services.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mark")
public class MarkController {

    @Autowired
    public MarkService markService;

    @PostMapping("/save")
    public void save(@RequestBody Mark mark) {
        markService.save(mark);
    }

    @GetMapping("/findByFilterSubject")
    public List<SubjectAndMark> findByFilterSubject(@RequestParam String name) {
        return markService.findByFilterSubject(name);
    }

    @GetMapping("/findByFilterPerson")
    public  List<PersonAndMark> findByFilterPerson(@RequestParam String subject) {
        return markService.findByFilterPerson(subject);
    }

}
