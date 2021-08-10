package com.example.school.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBUtils implements AutoCloseable {

    public void close(Connection connection, PreparedStatement statement, ResultSet resultSet) throws Exception {
        connection.close();
        statement.close();
        resultSet.close();
    }

    public void close(Connection connection, PreparedStatement statement) throws Exception {
        connection.close();
        statement.close();
    }

    @Override
    public void close() throws Exception {

    }
}
