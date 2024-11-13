package com.example.library_management_system.exceptions;

public class InvalidMySQLCredentialsException extends MySQLConnectionException {
    public InvalidMySQLCredentialsException(String message) {

        super(message);
    }

    public InvalidMySQLCredentialsException(String message, Throwable cause){
        super(message, cause);
    }
}
