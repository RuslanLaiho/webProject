package com.example.school.services;

import com.example.school.dto.Person;
import com.example.school.dto.Subject;
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
public class SubjectService {

    @Autowired
    public DataSource dataSource;

    public List<Subject> selectAllSubject() {

        List<Subject> subjectList = new ArrayList<Subject>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM subjects;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Subject timeSubject = new Subject();
                timeSubject.subject = resultSet.getString(1);

                subjectList.add(timeSubject);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


        return subjectList;
    }

    public void insertSubject(String subject) {
        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO Subjects VALUES ('"+subject+"') ");
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dbUtils.close(connection, statement);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
