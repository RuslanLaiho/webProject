package com.example.school.services;

import com.example.school.dto.Class;
import com.example.school.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService {

    @Autowired
    public DataSource dataSource;

    public List<Class> selectAllClass() {
        ArrayList<Class> classArrayList = new ArrayList<Class>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT class_id, class_name, class_year, person.name FROM Class JOIN Person ON (person.person_id = class.teacher_id)");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Class timeClass = new Class();
                timeClass.classId = resultSet.getInt(1);
                timeClass.className = resultSet.getString(2);
                timeClass.classYear = resultSet.getInt(3);
                timeClass.classTeacher = resultSet.getString(4);

                classArrayList.add(timeClass);
            }

        } catch (SQLException ex) {
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return classArrayList;
    }

    public ArrayList<Person> selectListOfClass(int year, String className) {
        ArrayList<Person> personArrayList = new ArrayList<Person>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT person.person_id, person.name, person.occupation FROM class_student " +
                    "JOIN Person ON (class_student.student_id = person.person_id) WHERE class_student.class_id = " +
                    "(SELECT class_id FROM Class WHERE class_year = " + year + " and class_name = '" + className + "');");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person timePerson = new Person();
                timePerson.personId = resultSet.getInt(1);
                timePerson.name = resultSet.getString(2);
                timePerson.occupation = resultSet.getString(3);

                personArrayList.add(timePerson);
            }


        } catch (SQLException ex) {
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return personArrayList;
    }

    public void insertClass(String className, int classYear, String classTeacher) {
        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO Class (class_name, class_year, teacher_id) " +
                    "VALUES ('" + className + "', " + classYear + ", (SELECT person_id " +
                    "From person " +
                    "WHERE name = '" + classTeacher + "')); ");
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

    public Class selectClass(int year, String className) {
        Class foundClass = new Class();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("SELECT class_id, class_name, class_year, person.name as teacher " +
                    "FROM class " +
                    "JOIN person " +
                    "ON teacher_id = person_id " +
                    "WHERE class_name = '" + className + "' AND class_year = " + year + ";");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                foundClass.classId = resultSet.getInt(1);
                foundClass.className = resultSet.getString(2);
                foundClass.classYear = resultSet.getInt(3);
                foundClass.classTeacher = resultSet.getString(4);

            }

        } catch (SQLException ex) {
        } finally {
            try {
                dbUtils.close(connection, statement, resultSet);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return foundClass;
    }

    public void updateClassYear(int updateYear, int classYear, String className) {
        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE class\n" +
                    "SET class_year = " + updateYear + "\n" +
                    "WHERE class_name = '" + className + "'\n" +
                    "AND class_year = " + classYear + ";");
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
