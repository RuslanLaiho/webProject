package com.example.school.controllers;

import com.example.school.dto.Class;
import com.example.school.dto.InsertClassDTO;
import com.example.school.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/class")
public class ClassController {


    @Autowired
    public ClassService classService;


    @GetMapping("/getAll")
    public List<Class> getAll() {
        return classService.getAll();
    }

    @GetMapping("/find")
    public Class find(int year, String className) {
        return classService.find(year, className);
    }

    @PostMapping("/save")
    public void save(@RequestBody InsertClassDTO insertClassDTO) {
        classService.save(insertClassDTO.className, insertClassDTO.classYear, insertClassDTO.classTeacher, insertClassDTO.birthYear, insertClassDTO.phone);
    }

    @PutMapping ("/update")
    public void Update(@RequestBody int updateYear, @RequestBody int classYear, @RequestBody String className) {
        classService.update(updateYear, classYear, className);
    }

}
