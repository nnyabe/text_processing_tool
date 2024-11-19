package com.example.library_management_system.utils;

import com.example.library_management_system.exceptions.InvalidMySQLCredentialsException;
import com.example.library_management_system.exceptions.MySQLConnectionException;
import com.example.library_management_system.exceptions.MySQLTimeoutException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Utility class for managing MySQL database connections and running SQL scripts.
 */
public class DBConnection {

    private final static String dbUrl = "jdbc:mysql://localhost:3306/library";  // Database URL
    private final static String user = "chamamme";  // Database user
    private final static String passWord = "1Am@Kr0fr0m$$";  // Database password

    /**
     * Creates a connection to the MySQL database.
     * @throws MySQLConnectionException if connection fails
     */
    public static Connection createConnection() throws MySQLConnectionException {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, user, passWord);
            System.out.println("Connected to the database successfully!");
            return connection;
        } catch (SQLException e) {
            // Handling specific SQL exceptions
            if (e.getMessage().contains("Access denied for user")) {
                throw new InvalidMySQLCredentialsException("Invalid MySQL credentials: " + e.getMessage(), e);
            }
            if (e.getMessage().contains("Communications link failure")) {
                throw new MySQLTimeoutException("MySQL connection timed out: " + e.getMessage(), e);
            }
            throw new MySQLConnectionException("Error establishing MySQL connection: " + e.getMessage(), e);
        }
    }

    /**
     * Runs an SQL initialization script from a file.
     * @param path the path to the SQL script file
     */
    public static void runSQLInitialization(String path) {
        try (Connection connection = DBConnection.createConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(path))) {

            StringBuilder query = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append('\n');
            }

            String[] queries = query.toString().split(";");
            for (String sql : queries) {
                if (!sql.trim().isEmpty()) {
                    statement.executeUpdate(sql);  // Execute each query
                }
            }
            System.out.println("SQL script executed successfully.");
        } catch (SQLException | MySQLConnectionException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs an SQL data input script from a file.
     * @param path the path to the SQL script file
     */
    public static void runSQLDataInputs(String path) {
        try (Connection connection = DBConnection.createConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader(path))) {

            StringBuilder query = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append('\n');
            }

            String[] queries = query.toString().split(";");
            for (String sql : queries) {
                if (!sql.trim().isEmpty()) {
                    statement.executeUpdate(sql);  // Execute each data input query
                }
            }
            System.out.println("SQL script executed successfully.");
        } catch (SQLException | MySQLConnectionException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
