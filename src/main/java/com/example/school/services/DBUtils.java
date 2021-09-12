package com.example.school.services;

public class DBUtils  {

    public static void close(AutoCloseable... autoCloseables) {

        for (AutoCloseable autoCloseable:autoCloseables ) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
