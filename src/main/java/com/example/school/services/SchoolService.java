package com.example.school.services;

import com.example.school.dto.Class;
import com.example.school.dto.Mark;
import com.example.school.dto.Person;
import com.example.school.dto.SubjectAndPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {

    @Autowired
    public DataSource dataSource;



}
