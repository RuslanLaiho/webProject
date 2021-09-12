package com.example.school.services;

import com.example.school.dto.Person;
import com.example.school.dto.StudentAndClass;
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
public class PersonService {

    @Autowired
    public DataSource dataSource;

    public List<Person> selectAllPerson() {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        ArrayList<Person> personList = new ArrayList<Person>();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Person ORDER BY occupation, name;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person timePerson = new Person();
                timePerson.personId = resultSet.getInt(1);
                timePerson.name = resultSet.getString(2);
                timePerson.occupation = resultSet.getString(3);

                personList.add(timePerson);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtils.close(connection,statement,resultSet);
        }


        return personList;
    }

    public List<SubjectAndPerson> selectPersonByMark(int mark) {
        ArrayList<SubjectAndPerson> subjectAndPersonArrayList = new ArrayList<SubjectAndPerson>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT subject, person.name FROM marks JOIN person ON person.person_id = marks.student_id WHERE mark = ?; ");
            statement.setInt(1,mark);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                SubjectAndPerson timeSubjectAndPerson = new SubjectAndPerson();
                timeSubjectAndPerson.subject = resultSet.getString(1);
                timeSubjectAndPerson.name = resultSet.getString(2);


                subjectAndPersonArrayList.add(timeSubjectAndPerson);
            }

        } catch (SQLException ex) {
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }

        return subjectAndPersonArrayList;
    }

    public void insertPerson(String name, String occupation, int birthYear, String phone) {
        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        if ((occupation.equals("Учитель")) || (occupation.equals("Ученик"))) {

            try {

                connection = dataSource.getConnection();
                statement = connection.prepareStatement("INSERT INTO person (name, occupation, birthyear, phone) VALUES (?, ?::occupation, ?, ?); ");
                statement.setString(1, name);
                statement.setString(2, occupation);
                System.out.println(birthYear);
                statement.setInt(3, birthYear);
                statement.setString(4, phone);
                statement.execute();


            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                dbUtils.close(connection, statement);
            }
        }
    }

    public List<StudentAndClass> selectAllStudent() {
        ArrayList<StudentAndClass> studentAndClassArrayList = new ArrayList<StudentAndClass>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT name, class_year, class_name, birthyear, phone FROM person p " +
                    "JOIN class_student cs ON p.person_id = cs.student_id " +
                    "JOIN class c ON cs.class_id = c.class_id; ");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                StudentAndClass timeStudentAndClass = new StudentAndClass();
                timeStudentAndClass.studentName = resultSet.getString(1);
                timeStudentAndClass.classYear = resultSet.getInt(2);
                timeStudentAndClass.className = resultSet.getString(3);
                timeStudentAndClass.birthyear = resultSet.getInt(4);
                timeStudentAndClass.phone = resultSet.getString(5);

                studentAndClassArrayList.add(timeStudentAndClass);
            }

        } catch (SQLException ex) {
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }

        return studentAndClassArrayList;
    }

    public Person selectPerson(String name, int birthYear, String phone) {
        Person person = new Person();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM person " +
                    "WHERE name = ? " +
                    "AND birthyear = ? " +
                    "AND phone = ?;");
            statement.setString(1,name);
            statement.setInt(2,birthYear);
            statement.setString(3,phone);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                person.personId = resultSet.getInt(1);
                person.name = resultSet.getString(2);
                person.occupation = resultSet.getString(3);
                person.birthYear = resultSet.getInt(4);
                person.phone = resultSet.getString(5);
            }

        } catch (SQLException ex) {
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }

        return person;
    }

    public List<Person> selectAllTheacher() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        ArrayList<Person> personList = new ArrayList<Person>();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT * FROM Person WHERE occupation = 'Учитель' ORDER BY occupation, name;");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person timePerson = new Person();
                timePerson.personId = resultSet.getInt(1);
                timePerson.name = resultSet.getString(2);
                timePerson.occupation = resultSet.getString(3);
                timePerson.birthYear = resultSet.getInt(4);
                timePerson.phone = resultSet.getString(5);

                personList.add(timePerson);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtils.close(connection, statement, resultSet);
        }


        return personList;
    }
}
