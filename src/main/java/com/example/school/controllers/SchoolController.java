package com.example.school.controllers;

import com.example.school.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

    @Autowired
    public SchoolService schoolService;

    @GetMapping("/test")
    public String test() {
        return "ZAWARDOOOO";
    }

}
