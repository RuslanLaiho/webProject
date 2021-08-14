package com.example.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class StudentAndClassService {

    @Autowired
    public DataSource dataSource;

    public void insertStudentAndClass(String studentName, int classYear, String className) {

        Connection connection = null;
        PreparedStatement statement = null;
        DBUtils dbUtils = new DBUtils();

        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO class_student (student_id, class_id) \n" +
                    "VALUES ((SELECT person_id\n" +
                    "FROM person\n" +
                    "WHERE name = '" + studentName + "'),\n" +
                    "(SELECT class_id " +
                    "FROM class " +
                    "WHERE class_name = '" + className + "' " +
                    "AND class_year = " + classYear + ") ); ");
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
