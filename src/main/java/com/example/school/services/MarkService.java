package com.example.school.services;

import com.example.school.dto.*;
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
public class MarkService {

    @Autowired
    public DataSource dataSource;

    public void insertMark(Mark mark) {

        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO marks VALUES ('" + mark.subject + "', " + mark.mark + ", " +
                    "(SELECT person_id FROM person WHERE name = '" + mark.studentName + "'), " +
                    "(SELECT person_id FROM person WHERE name = '" + mark.teacherName + "'));");
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

    public List<SubjectAndMark> selectAllMarkOfStudent(String name) {

        ArrayList<SubjectAndMark> subjectAndMarkList = new ArrayList<SubjectAndMark>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT subject, mark\n" +
                    "FROM Marks\n" +
                    "WHERE student_id = (SELECT person_id\n" +
                    "FROM person\n" +
                    "WHERE name = '" + name + "');");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SubjectAndMark timeSubjectAndMark = new SubjectAndMark();
                timeSubjectAndMark.subject = resultSet.getString(1);
                timeSubjectAndMark.mark = resultSet.getInt(2);

                subjectAndMarkList.add(timeSubjectAndMark);
            }


        } catch (SQLException ex) {
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return subjectAndMarkList;
    }

    public List<PersonAndMark> selectMarkOfSubject(String subject) {
        ArrayList<PersonAndMark> personAndMarkList = new ArrayList<PersonAndMark>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT person.name, mark FROM Marks\n" +
                                                        "JOIN person ON person_id=student_id\n" +
                                                        "WHERE subject = '"+subject+"';");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PersonAndMark timePersonAndMark = new PersonAndMark();
                timePersonAndMark.personName = resultSet.getString(1);
                timePersonAndMark.mark = resultSet.getInt(2);

                personAndMarkList.add(timePersonAndMark);
            }


        } catch (SQLException ex) {
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return personAndMarkList;
    }
}
