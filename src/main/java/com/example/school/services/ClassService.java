package com.example.school.services;

import com.example.school.dto.Class;
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
public class ClassService {

    @Autowired
    public DataSource dataSource;

    public List<Class> getAll() {
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
            dbUtils.close(connection, statement, resultSet);
        }

        return classArrayList;
    }

    public void save(String className, int classYear, String classTeacher, int birthYear, String phone) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        DBUtils dbUtils = new DBUtils();
        Integer teacherId = null;

        Class cheсkClass = find(classYear, className);

        if(cheсkClass.className==null) {
            try {
                connection = dataSource.getConnection();
                statement = connection.prepareStatement("SELECT person_id FROM person\n" +
                        "WHERE name = ? " +
                        "AND birthyear = ? " +
                        "AND phone = ?;");
                statement.setString(1, classTeacher);
                statement.setInt(2, birthYear);
                statement.setString(3, phone);

                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    teacherId = resultSet.getInt(1);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                dbUtils.close(connection, statement, resultSet);
            }

            if (teacherId != null) {
                try {

                    connection = dataSource.getConnection();
                    statement = connection.prepareStatement("INSERT INTO Class (class_name, class_year, teacher_id) " +
                            "VALUES (?, ?, ?); ");
                    statement.setString(1, className);
                    statement.setInt(2, classYear);
                    statement.setInt(3, teacherId);
                    statement.execute();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    dbUtils.close(connection, statement);
                }
            } else {
                System.out.println("Нет учителя");
            }
        } else {
            System.out.println("Такой класс уже сужествует");
        }

    }

    public Class find(int year, String className) {
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
                    "WHERE class_name = ? AND class_year = ?;");
            statement.setString(1,className);
            statement.setInt(2,year);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                foundClass.classId = resultSet.getInt(1);
                foundClass.className = resultSet.getString(2);
                foundClass.classYear = resultSet.getInt(3);
                foundClass.classTeacher = resultSet.getString(4);

            }

        } catch (SQLException ex) {
        } finally {
          dbUtils.close(connection, statement, resultSet);
        }

        return foundClass;
    }

    public void update(int updateYear, int classYear, String className) {
        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE class\n" +
                    "SET class_year = ?\n" +
                    "WHERE class_name = ?\n" +
                    "AND class_year = ?;");
            statement.setInt(1,updateYear);
            statement.setString(2,className);
            statement.setInt(3,classYear);
            statement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            dbUtils.close(connection, statement);
        }
    }
}
