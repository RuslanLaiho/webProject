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

    public void save(Mark mark) {

        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO marks VALUES (?, ?, " +
                    "(SELECT person_id FROM person WHERE name = ?), " +
                    "(SELECT person_id FROM person WHERE name = ?));");
            statement.setString(1, mark.subject);
            statement.setInt(2, mark.mark);
            statement.setString(3, mark.studentName);
            statement.setString(4, mark.teacherName);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtils.close(connection, statement);
        }

    }

    public List<SubjectAndMark> findByFilterSubject(String name) {

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
                    "WHERE name = ?);");
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SubjectAndMark timeSubjectAndMark = new SubjectAndMark();
                timeSubjectAndMark.subject = resultSet.getString(1);
                timeSubjectAndMark.mark = resultSet.getInt(2);

                subjectAndMarkList.add(timeSubjectAndMark);
            }


        } catch (SQLException ex) {
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }

        return subjectAndMarkList;
    }

    public List<PersonAndMark> findByFilterPerson(String subject) {
        ArrayList<PersonAndMark> personAndMarkList = new ArrayList<PersonAndMark>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT person.name, mark FROM Marks\n" +
                                                        "JOIN person ON person_id=student_id\n" +
                                                        "WHERE subject = ?;");
            statement.setString(1,subject);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PersonAndMark timePersonAndMark = new PersonAndMark();
                timePersonAndMark.personName = resultSet.getString(1);
                timePersonAndMark.mark = resultSet.getInt(2);

                personAndMarkList.add(timePersonAndMark);
            }


        } catch (SQLException ex) {
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }

        return personAndMarkList;
    }
}
