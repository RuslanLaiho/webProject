package com.example.school.services;

public class DBUtils  {

    public static void close(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
