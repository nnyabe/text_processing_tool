package com.example.library_management_system.utils;

import com.example.library_management_system.exceptions.MySQLConnectionException;

public class TestDb {

    public static void main(String[] args) {

        try{
            DBConnection conn = new DBConnection();
            conn.createConnection();
        } catch (MySQLConnectionException e) {
            throw new RuntimeException(e);
        }
    }
}
