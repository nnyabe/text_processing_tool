package com.example.library_management_system.exceptions;

public class MySQLTimeoutException extends MySQLConnectionException {
    public MySQLTimeoutException(String message) {
        super(message);
    }

    public MySQLTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}