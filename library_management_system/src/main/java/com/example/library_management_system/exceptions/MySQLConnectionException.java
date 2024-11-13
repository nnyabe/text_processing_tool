package com.example.library_management_system.exceptions;

public class MySQLConnectionException extends Exception {
    public MySQLConnectionException(String message) {
        super(message);
    }

    public MySQLConnectionException(String message, Throwable cause){
        super(message, cause);
    }
}
