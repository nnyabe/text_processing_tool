package com.example.library_management_system.utils;
import com.example.library_management_system.exceptions.InvalidMySQLCredentialsException;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.exceptions.MySQLTimeoutException;

import java.sql.*;


public class DBConnection {

    private final static String dbUrl = "jdbc:mysql://localhost:3306/library";
    private final static String user = "chamamme";
    private final static String passWord = "1Am@Kr0fr0m$$";
    public static Connection createConnection() throws MySQLConnectionException {
        try{
            Connection connection = DriverManager.getConnection(dbUrl, user, passWord);
            System.out.println("Connected to the database successfully!");
            return connection;
        }
        catch (SQLException e) {
            if(e.getMessage().contains("Access denied for user")){
                throw new InvalidMySQLCredentialsException("Invalid MySQL credentials: " + e.getMessage(), e);
            }
            if(e.getMessage().contains("Communications link failure")){
                throw new MySQLTimeoutException("MySQL connection timed out: " + e.getMessage(), e);
            }
            throw new MySQLConnectionException("Error establishing MySQL connection :" + e.getMessage(), e);
        }
    }


}
